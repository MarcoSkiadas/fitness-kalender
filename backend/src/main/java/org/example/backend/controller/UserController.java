package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.dto.RegisterUserDTO;
import org.example.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void createUser(@RequestBody RegisterUserDTO newUser) {
        userService.createNewUser(newUser);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/friend/{userId}/{friendId}")
    public void addFriend(@PathVariable String friendId, @PathVariable String userId) {
        userService.addNewFriend(friendId,userId);
    }
}
