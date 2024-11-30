package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.FiKaUser;
import org.example.backend.model.dto.RegisterUserDTO;
import org.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public FiKaUser getUserById(@PathVariable String userId) {

        return userService.getUserById(userId);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<FiKaUser> getUser() {

        return userService.getUser();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void createUser(@RequestBody RegisterUserDTO newUser) {
        userService.createUser(newUser);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login", produces = "text/plain")
    public String login() {
        return userService.login();
    }
}
