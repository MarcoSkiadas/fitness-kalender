package org.example.backend.model;


public record SetExercise(
        String exerciseName,
        int defaultSets,
        int defaultRepetitions
) {}
