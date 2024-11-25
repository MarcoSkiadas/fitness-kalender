package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.service.WorkoutSessionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workoutsession")
@RequiredArgsConstructor
public class WorkoutSessionController {
    private final WorkoutSessionService workoutSessionService;
}
