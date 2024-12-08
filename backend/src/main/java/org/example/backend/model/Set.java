package org.example.backend.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Set(
        @Id
        String id,
        String userId,
        String name,
        SetExercise[] exercise,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
