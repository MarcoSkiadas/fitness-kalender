package org.example.backend.service;

import org.example.backend.model.FiKaUser;
import org.example.backend.model.Set;
import org.example.backend.model.SetExercise;
import org.example.backend.repository.SetRepo;
import org.example.backend.repository.UserRepo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SetServiceTest {

    private final SetRepo mockRepo = mock(SetRepo.class);
    private final UserRepo mockUserRepo = mock(UserRepo.class);
    private final IdService mockIdService = mock(IdService.class);
    private final DateTimeService mockDateTimeService = mock(DateTimeService.class);
    private final UserService userService = new UserService(mockUserRepo, mockIdService);
    private final SetService setService = new SetService(mockRepo, mockIdService,mockDateTimeService,userService);

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
        LocalDateTime now = LocalDateTime.now();
        SetExercise setExercise = new SetExercise("TestExercise",3,3);
        Set testSet = new Set("1","1","TestSet",new SetExercise[]{setExercise},now,now);
        Set newSet = new Set(null,"1","TestSet",new SetExercise[]{setExercise},null,null);
        List<Set> sets = new ArrayList<>();
        sets.add(testSet);
        sets.add(newSet);
        when(mockRepo.findAll()).thenReturn(sets);
        List<Set> actualSets = setService.getSet();
        assertEquals(testSet, actualSets.getFirst());
        verify(mockRepo).findAll();
    }

    @Test
    void createSet_shouldReturnSet_whenCalledInitially() throws RuntimeException {
        LocalDateTime now = LocalDateTime.now();
        SetExercise setExercise = new SetExercise("TestExercise",3,3);
        Set testSet = new Set("1","1","TestSet",new SetExercise[]{setExercise},now,now);
        Set newSet = new Set(null,"1","TestSet",new SetExercise[]{setExercise},null,null);
        FiKaUser testAppUser = new FiKaUser("1", "TestUser", "swordfish", "USER", LocalDateTime.now(),new Set[0]);
        mockUserRepo.save(testAppUser);
        mockRepo.save(testSet);
        when(mockIdService.generateUUID()).thenReturn("1");
        when(mockDateTimeService.now()).thenReturn(now);
        when(mockRepo.save(any(Set.class))).thenReturn(testSet);
        when(mockUserRepo.findById("1")).thenReturn(Optional.of(testAppUser));
        Set actualSet = setService.createSet(newSet);
        assertEquals(testSet, actualSet);
    }
    @Test
    void updateSet_shouldReturnSet_whenCalledInitially() throws RuntimeException {
        LocalDateTime now = LocalDateTime.now();
        SetExercise setExercise1 = new SetExercise("TestExercise1",3,3);
        SetExercise setExercise2 = new SetExercise("TestExercise2",4,4);

        Set testSet = new Set("1","1","TestSet",new SetExercise[]{setExercise1},now,now);
        Set newSet = new Set("2","1","TestSet",new SetExercise[]{setExercise2},null,null);
        FiKaUser testAppUser = new FiKaUser("1", "TestUser", "swordfish", "USER", LocalDateTime.now(),new Set[]{testSet,newSet});
        mockUserRepo.save(testAppUser);
        mockRepo.save(testSet);
        when(mockRepo.save(any(Set.class))).thenReturn(testSet);
        when(mockUserRepo.findById("1")).thenReturn(Optional.of(testAppUser));
        setService.updateSet(newSet,"1","1");
        Set[] updaters = testAppUser.sets();
        assertEquals(Optional.of(setExercise1), Arrays.stream(updaters[0].exercise()).findFirst());
    }


}