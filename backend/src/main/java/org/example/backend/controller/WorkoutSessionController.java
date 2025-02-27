package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.WorkoutSession;
import org.example.backend.service.WorkoutSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutSession>> getWorkoutSessionByUserId(@PathVariable String userId) {
        List<WorkoutSession> sessions = workoutSessionService.getWorkoutSessionByUserId(userId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(sessions);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/delete/{workoutSessionId}")
    public void deleteWorkoutSession(@PathVariable String workoutSessionId) {
        workoutSessionService.deleteWorkoutSession(workoutSessionId);
    }
}
