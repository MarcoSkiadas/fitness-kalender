package org.example.backend.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.backend.model.FiKaUser;
import org.example.backend.model.dto.FiKaUserResponse;
import org.example.backend.repository.UserRepo;
import org.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserRepo userRepo;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me")
    public FiKaUserResponse getMe(@AuthenticationPrincipal OAuth2User user) {
        if (SecurityContextHolder.getContext().getAuthentication().getName().matches("^\\d+$")) {
            FiKaUser fiKaUser = userRepo.findById(user.getName())
                    .orElseThrow();
            return FiKaUserResponse.fromAppUser(fiKaUser);

        }
        return userService.getUserByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login", produces = "text/plain")
    public String login() {
        return userService.login();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
        SecurityContextHolder.clearContext();
    }
}

