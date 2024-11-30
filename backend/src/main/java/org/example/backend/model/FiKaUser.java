package org.example.backend.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record FiKaUser(
        @Id
        String id,
        String username,
        String password,
        String role,
        LocalDateTime createDate,
        Set[] sets
) {
}
