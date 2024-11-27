package org.example.backend.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public record WorkoutSession(
        @Id
        String id,
        String userId,
        Date workoutDate,
        WorkoutExercise workoutExercise,
        Date createdAt
) {
}
