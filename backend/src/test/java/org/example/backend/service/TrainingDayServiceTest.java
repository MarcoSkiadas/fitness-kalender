package org.example.backend.service;

import org.example.backend.model.Friend;
import org.example.backend.model.TrainingDay;
import org.example.backend.repository.TrainingDayRepo;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TrainingDayServiceTest {

    private final TrainingDayRepo mockRepo = mock(TrainingDayRepo.class);
    private final IdService mockIdService = mock(IdService.class);
    private final TrainingDayService trainingDayService = new TrainingDayService(mockRepo,mockIdService);

    @Test
    void createTrainingDay_shouldSaveTrainingDay_whenCalledInitially() {
        LocalDateTime now = LocalDateTime.now();
        Friend[] participants = new Friend[]{new Friend("1","TestFriend1"), new Friend("2","TestFriend2")};
        TrainingDay expectedTrainingDay = new TrainingDay("1","1",participants,now);
        when(mockIdService.generateUUID()).thenReturn("1");
        when(mockRepo.save(any(TrainingDay.class))).thenReturn(expectedTrainingDay);
        TrainingDay actualTrainingDay = trainingDayService.createTrainingDay(new TrainingDay("1","1",participants,now));
        assertEquals(expectedTrainingDay, actualTrainingDay);
        verify(mockRepo, times(1)).save(actualTrainingDay);
    }
}