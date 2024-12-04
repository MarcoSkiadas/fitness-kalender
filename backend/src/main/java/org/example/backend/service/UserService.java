package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.exeptions.InvalidIdException;
import org.example.backend.model.FiKaUser;
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
        if (userRepo.findByUsername(registerUserDTO.username()).isPresent()) {
            throw new InvalidIdException("Username "+registerUserDTO.username()+" already exists!");
        }
        FiKaUser newFiKaUser = new FiKaUser(idService.generateUUID(), registerUserDTO.username(), encoder.encode(registerUserDTO.password()),"USER", LocalDateTime.now(), new Set[0]);
        userRepo.save(newFiKaUser);

    }

}
