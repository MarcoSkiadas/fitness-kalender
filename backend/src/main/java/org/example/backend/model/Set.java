package org.example.backend.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public record Set(
        @Id
        String id,
        String userId,
        String name,
        SetExercise[] exercise,
        Date createdAt,
        Date updatedAt
) {
}
