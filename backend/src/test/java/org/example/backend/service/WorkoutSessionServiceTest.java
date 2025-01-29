package org.example.backend.service;

import org.example.backend.exceptions.InvalidIdException;
import org.example.backend.model.Set;
import org.example.backend.model.WorkoutExercise;
import org.example.backend.model.WorkoutSession;
import org.example.backend.repository.WorkoutSessionRepo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkoutSessionServiceTest {

    private final WorkoutSessionRepo mockRepo = mock(WorkoutSessionRepo.class);
    private final IdService mockIdService = mock(IdService.class);
    private final DateTimeService mockDateTimeService = mock(DateTimeService.class);
    private final WorkoutSessionService workoutSessionService = new WorkoutSessionService(mockRepo, mockIdService, mockDateTimeService);

    @Test
    void getWorkoutSessionById_shouldReturnWorkoutSession_whenCalledBySetId() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();
        WorkoutExercise[] workoutExercises = new WorkoutExercise[]{new WorkoutExercise("workoutExercise1", 2,3,3), new WorkoutExercise("workoutExercise2", 3,3,3)};
        WorkoutSession expectedWorkoutSession = new WorkoutSession("1","1",date,workoutExercises,now);
        mockRepo.save(expectedWorkoutSession);
        when(mockRepo.findById("1")).thenReturn(Optional.of(expectedWorkoutSession));
        WorkoutSession actualWorkoutSession = workoutSessionService.getWorkoutSessionById("1");
        assertEquals(expectedWorkoutSession, actualWorkoutSession);
        verify(mockRepo).findById("1");
    }

    @Test
    void getWorkoutSessionById_shouldReturnException_whenCalledByWrongId() {
        when(mockRepo.findById("1")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> workoutSessionService.getWorkoutSessionById("1"));
        verify(mockRepo).findById("1");
    }

    @Test
    void getWorkoutSession_shouldReturnWorkoutSessions_whenCalled() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();
        WorkoutExercise[] workoutExercises = new WorkoutExercise[]{new WorkoutExercise("workoutExercise1", 2,3,3), new WorkoutExercise("workoutExercise2", 3,3,3)};
        List<WorkoutSession> workoutSessions = new ArrayList<>();
        WorkoutSession workoutSession1 = new WorkoutSession("1","1",date,workoutExercises,now);
        WorkoutSession workoutSession2 = new WorkoutSession("1","1",date,workoutExercises,now);
        workoutSessions.add(workoutSession1);
        workoutSessions.add(workoutSession2);
        mockRepo.save(workoutSession1);
        mockRepo.save(workoutSession2);
        when(mockRepo.findAll()).thenReturn((workoutSessions));
        List<WorkoutSession> actualWorkoutSession = workoutSessionService.getWorkoutSession();
        assertEquals(workoutSessions, actualWorkoutSession);
        verify(mockRepo).findAll();
    }

    @Test
    void createWorkoutSession_shouldReturnWorkoutSession_whenCalled() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();
        WorkoutExercise[] workoutExercises = new WorkoutExercise[]{new WorkoutExercise("workoutExercise1", 2,3,3), new WorkoutExercise("workoutExercise2", 3,3,3)};
        WorkoutSession expectedWorkoutSession = new WorkoutSession("1","1",date,workoutExercises,now);
        mockRepo.save(expectedWorkoutSession);
        when(mockRepo.save(expectedWorkoutSession)).thenReturn(expectedWorkoutSession);
        when(mockIdService.generateUUID()).thenReturn("1");
        when(mockDateTimeService.now()).thenReturn(now);
        when(mockRepo.save(any(WorkoutSession.class))).thenReturn(expectedWorkoutSession);
        WorkoutSession actualWorkoutSession = workoutSessionService.createWorkoutSession(expectedWorkoutSession);
        assertEquals(expectedWorkoutSession, actualWorkoutSession);
        verify(mockRepo).save(expectedWorkoutSession);

    }
    @Test
    void getWorkoutSessionById_shouldReturnWorkoutSession_whenCalled() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();
        WorkoutExercise[] workoutExercises = new WorkoutExercise[]{new WorkoutExercise("workoutExercise1", 2,3,3), new WorkoutExercise("workoutExercise2", 3,3,3)};
        List<WorkoutSession> workoutSessions = new ArrayList<>();
        WorkoutSession workoutSession1 = new WorkoutSession("1","1",date,workoutExercises,now);
        WorkoutSession workoutSession2 = new WorkoutSession("1","1",date,workoutExercises,now);
        workoutSessions.add(workoutSession1);
        workoutSessions.add(workoutSession2);
        mockRepo.save(workoutSession1);
        mockRepo.save(workoutSession2);
        when(mockRepo.findByUserId("1")).thenReturn((workoutSessions));
        List<WorkoutSession> actualWorkoutSession = workoutSessionService.getWorkoutSessionByUserId("1");
        assertEquals(workoutSessions, actualWorkoutSession);
        verify(mockRepo).findByUserId("1");

    }
    @Test
    void deleteWorkoutSession_shouldReturnTrue_whenCalled() {
        when(mockRepo.existsById("1")).thenReturn(Boolean.TRUE);
        workoutSessionService.deleteWorkoutSession("1");
        verify(mockRepo, times(1)).existsById("1");
        verify(mockRepo, times(1)).deleteById("1");
    }
    @Test
    void deleteWorkoutSession_shouldReturnException_whenCalledWithWrongId() {
        when(mockRepo.existsById("1")).thenReturn(Boolean.FALSE);
        assertThrows(InvalidIdException.class, () -> workoutSessionService.deleteWorkoutSession("1"));
    }
}