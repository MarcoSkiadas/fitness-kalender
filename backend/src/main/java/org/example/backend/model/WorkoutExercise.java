package org.example.backend.model;

public record WorkoutExercise(
        String exerciseName,
        int Sets,
        int repetitions,
        int weight
) {
}
