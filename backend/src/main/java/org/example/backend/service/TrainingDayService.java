package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.TrainingDay;
import org.example.backend.repository.TrainingDayRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingDayService {

    private final TrainingDayRepo trainingDayRepo;
    private final IdService idService;

    public TrainingDay createTrainingDay(TrainingDay trainingDay) {

        return trainingDayRepo.save(new TrainingDay(idService.generateUUID(),trainingDay.creatorId(),trainingDay.participants(),trainingDay.trainingTime()));
    }

}
