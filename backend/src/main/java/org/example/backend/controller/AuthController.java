package org.example.backend.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.backend.repository.UserRepo;
import org.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepo userRepo;
    private final UserService userService;

    @GetMapping
    public String getMe(){
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
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

