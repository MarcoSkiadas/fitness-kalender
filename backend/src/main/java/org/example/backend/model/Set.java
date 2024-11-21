package org.example.backend.model;

import org.springframework.data.annotation.Id;

public record Set(
        @Id
        String id,
        String userId,
        String userName,
        Exercise[] exercise



) {
}
