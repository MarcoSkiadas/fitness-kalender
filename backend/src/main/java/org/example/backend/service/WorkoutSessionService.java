package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.WorkoutSession;
import org.example.backend.repository.WorkoutSessionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutSessionService {
    private final WorkoutSessionRepo workoutSessionRepo;

    public WorkoutSession getWorkoutSessionById(String setId) {
        return workoutSessionRepo.findById(setId)
                .orElseThrow(() -> new RuntimeException("WorkoutSession not found"));
    }

    public List<WorkoutSession> getWorkoutSession() {
        return workoutSessionRepo.findAll();
    }

    public WorkoutSession createWorkoutSession(WorkoutSession workoutSession) {
        return workoutSessionRepo.save(workoutSession);
    }
}
