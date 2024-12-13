package org.example.backend.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

public record WorkoutSession(
        @Id
        String id,
        String userId,
        Date workoutDate,
        WorkoutExercise[] workoutExercise,
        LocalDateTime createdAt
) {
}
