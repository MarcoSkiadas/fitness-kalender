package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.User;
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
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<User> getUser() {
        return userService.getUser();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public User createSet(@RequestBody User user) {
        return userService.createUser(user);
    }
}
