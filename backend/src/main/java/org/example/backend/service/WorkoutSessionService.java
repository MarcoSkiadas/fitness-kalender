package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.repository.WorkoutSessionRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutSessionService {
    private final WorkoutSessionRepo workoutSessionRepo;

}
