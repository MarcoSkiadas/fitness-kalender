package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.exceptions.InvalidIdException;
import org.example.backend.model.FiKaUser;
import org.example.backend.model.Friend;
import org.example.backend.model.Set;
import org.example.backend.model.dto.FiKaUserResponse;
import org.example.backend.model.dto.RegisterUserDTO;
import org.example.backend.repository.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final IdService idService;
    private final Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FiKaUser fiKaUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("FiKaUser: " + username + " not Found!"));
        return new User(fiKaUser.username(), fiKaUser.password(), Collections.emptyList());
    }
    public FiKaUserResponse getUserByUsername(String username) throws UsernameNotFoundException {
        FiKaUser fiKaUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not Found!"));
        return FiKaUserResponse.fromAppUser(fiKaUser);
    }

    public String login() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    public void createNewUser(RegisterUserDTO registerUserDTO) {
        if (userRepo.findByUsername(registerUserDTO.username().toLowerCase()).isPresent()) {
            throw new InvalidIdException("Username "+registerUserDTO.username()+" already exists!");
        }
        FiKaUser newFiKaUser = new FiKaUser(idService.generateUUID(), registerUserDTO.username().toLowerCase(), encoder.encode(registerUserDTO.password()),"USER", LocalDateTime.now(), new Set[0], new Friend[0]);
        userRepo.save(newFiKaUser);

    }
    public FiKaUser getUserById(String userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new InvalidIdException("User: " + userId + " not Found!"));
    }

    public void addSetToUser(String userId, Set set) {
        FiKaUser fiKaUser = getUserById(userId);

        // Bestehendes Set-Array erweitern
        Set[] existingSets = fiKaUser.sets();
        Set[] updatedSets = Arrays.copyOf(existingSets, existingSets.length + 1);
        updatedSets[existingSets.length] = set; // Neues Set am Ende hinzufügen

        FiKaUser updatedUser = fiKaUser.withSets(updatedSets);

        userRepo.save(updatedUser);

    }
    public void saveUser(FiKaUser fiKaUser) {
        userRepo.save(fiKaUser);
    }

    public void addNewFriend(String friendId, String userId) throws InvalidIdException {
        if (userRepo.findById(userId).isEmpty()) {
            throw new InvalidIdException("User: " + userId + " not Found!");
        }
        if (userRepo.findById(friendId).isEmpty()) {
            throw new InvalidIdException("Friend with Id: " + friendId + " not Found!");
        }
        FiKaUser fiKaUser = getUserById(userId);
        FiKaUser newFriendFikaUser = getUserById(friendId);
        Friend newFriend = new Friend(friendId,newFriendFikaUser.username());
        for (Friend friend : fiKaUser.friends()) {
            if (friend.equals(newFriend)) {
                throw new InvalidIdException("Friend with Id: " + friendId + " already exists!");
            }}
        Friend[] actualFriends = fiKaUser.friends();
        Friend[] newFriends = new Friend[actualFriends.length + 1];
        System.arraycopy(actualFriends, 0, newFriends, 0, actualFriends.length);
        newFriends[actualFriends.length] = newFriend;
        fiKaUser = fiKaUser.withFriends(newFriends);
        userRepo.save(fiKaUser);
    }
    public void deleteSetFromUser(String userId, String setId) {
        FiKaUser fiKaUser = getUserById(userId);
        FiKaUser updatedUser = fiKaUser.deleteSetById(setId);
        userRepo.save(updatedUser);
    }
}
