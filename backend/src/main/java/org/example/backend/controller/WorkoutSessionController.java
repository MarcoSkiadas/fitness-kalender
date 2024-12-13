package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.WorkoutSession;
import org.example.backend.service.WorkoutSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workoutSession")
@RequiredArgsConstructor
public class WorkoutSessionController {
    private final WorkoutSessionService workoutSessionService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{workoutSessionId}")
    public WorkoutSession getWorkoutSessionById(@PathVariable String workoutSessionId) {
        return workoutSessionService.getWorkoutSessionById(workoutSessionId);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<WorkoutSession> getWorkoutSession() {
        return workoutSessionService.getWorkoutSession();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public WorkoutSession createWorkoutSession(@RequestBody WorkoutSession workoutSession) {
        return workoutSessionService.createWorkoutSession(workoutSession);
    }
}
