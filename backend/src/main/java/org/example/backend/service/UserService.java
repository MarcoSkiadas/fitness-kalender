package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.Set;
import org.example.backend.model.User;
import org.example.backend.model.dto.RegisterUserDTO;
import org.example.backend.repository.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final IdService idService;
    private final Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not Found!"));
        return null;
    }

    public String login() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    public User getUserById(String userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getUser() {

        return userRepo.findAll();
    }

    public void createUser(RegisterUserDTO registerUserDTO) {
        if (userRepo.findByUsername(registerUserDTO.username()).isPresent()) {
            throw new IllegalArgumentException("Username "+registerUserDTO.username()+" already exists!");
        }
        User newUser = new User(idService.generateUUID(), registerUserDTO.username(), encoder.encode(registerUserDTO.password()),"USER", LocalDateTime.now(), new Set[0]);
        userRepo.save(newUser);

    }

}
