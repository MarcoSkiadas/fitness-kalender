package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.TrainingDay;
import org.example.backend.service.TrainingDayService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/trainingDay")
@RequiredArgsConstructor
public class TrainingDayController {

    private final TrainingDayService trainingDayService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public TrainingDay createTrainingDay(@RequestBody TrainingDay trainingDay) {
        return trainingDayService.createTrainingDay(trainingDay);
    }
}
