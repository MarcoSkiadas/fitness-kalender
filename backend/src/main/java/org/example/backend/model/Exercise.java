package org.example.backend.model;

public record Exercise(
        String exerciseName,
        int defaultSets,
        int defaultRepetitions
) {
}
