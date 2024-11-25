package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

}
