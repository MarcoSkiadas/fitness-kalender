package org.example.backend.service;

import org.example.backend.model.Set;
import org.example.backend.model.SetExercise;
import org.example.backend.repository.SetRepo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SetServiceTest {

    private final SetRepo mockRepo = mock(SetRepo.class);
    private final IdService mockIdService = mock(IdService.class);
    private final DateTimeService mockDateTimeService = mock(DateTimeService.class);
    private final SetService setService = new SetService(mockRepo, mockIdService,mockDateTimeService);

    @Test
    void getSetById_shouldReturnSet_whenCalledWithRightId() throws RuntimeException {
        SetExercise setExercise = new SetExercise("TestExercise",3,3);
        Set testSet = new Set("1","1","TestSet",new SetExercise[]{setExercise},LocalDateTime.now(),LocalDateTime.now());
        mockRepo.save(testSet);
        when(mockRepo.findById("1")).thenReturn(Optional.of(testSet));
        Set actualSet = setService.getSetById("1");
        assertEquals(testSet, actualSet);
        verify(mockRepo).findById("1");
    }

    @Test
    void getSetById_shouldThrowException_whenCalledWithWrongId() throws RuntimeException {
        when(mockRepo.findById("1")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> setService.getSetById("1"));
        verify(mockRepo).findById("1");
    }

    @Test
    void getSet() {
    }

    @Test
    void createSet_shouldReturnSet_whenCalledInitially() throws RuntimeException {
        LocalDateTime now = LocalDateTime.now();
        SetExercise setExercise = new SetExercise("TestExercise",3,3);
        Set testSet = new Set("1","1","TestSet",new SetExercise[]{setExercise},now,now);
        Set newSet = new Set(null,"1","TestSet",new SetExercise[]{setExercise},null,null);
        mockRepo.save(testSet);
        when(mockIdService.generateUUID()).thenReturn("1");
        when(mockDateTimeService.now()).thenReturn(now);
        when(mockRepo.save(any(Set.class))).thenReturn(testSet);
        Set actualSet = setService.createSet(newSet);
        assertEquals(testSet, actualSet);
    }
}