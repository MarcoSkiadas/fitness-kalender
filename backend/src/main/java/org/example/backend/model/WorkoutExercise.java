package org.example.backend.model;

public record WorkoutExercise(
        String exerciseName,
        int sets,
        int repetitions,
        int weight
) {
}
