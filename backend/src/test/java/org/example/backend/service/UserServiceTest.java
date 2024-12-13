package org.example.backend.service;

import org.example.backend.exceptions.InvalidIdException;
import org.example.backend.model.FiKaUser;
import org.example.backend.model.Set;
import org.example.backend.model.SetExercise;
import org.example.backend.model.dto.FiKaUserResponse;
import org.example.backend.model.dto.RegisterUserDTO;
import org.example.backend.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepo mockRepo = mock(UserRepo.class);
    private final IdService mockUtils = mock(IdService.class);
    private final UserService userService = new UserService(mockRepo, mockUtils);
    private final SecurityContext mockedSecurityContext = mock(SecurityContext.class);
    private final Authentication mockedAuthentication = mock(Authentication.class);

    @BeforeEach
    void setUp() {
        String userId = "1";
        String password = "swordfish";
        FiKaUser fiKaTestUser = new FiKaUser(userId,"TestUser",password,"User", LocalDateTime.now(),new Set[0]);
        mockRepo.save(fiKaTestUser);
    }

    @Test
    void loadUserByUsername_shouldReturnUser_whenCalled() throws UsernameNotFoundException {
        FiKaUser testAppUser = new FiKaUser("1", "TestUser", "swordfish", "USER", LocalDateTime.now(),new Set[0]);
        mockRepo.save(testAppUser);
        UserDetails expectedUser = new User(testAppUser.username(), testAppUser.password(), Collections.emptyList());
        when(mockRepo.findByUsername("TestUser")).thenReturn(Optional.of(testAppUser));
        UserDetails actualUser = userService.loadUserByUsername("TestUser");
        userService.loadUserByUsername("TestUser");
        assertEquals(expectedUser, actualUser);
        verify(mockRepo, times(2)).findByUsername("TestUser");
    }

    @Test
    void loadUserByUsername_shouldThrowException_whenCalledWithWrongUser() throws UsernameNotFoundException {
        when(mockRepo.findByUsername("TestUser")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("TestUser"));
        verify(mockRepo).findByUsername("TestUser");
    }

    @Test
    void getUserByUsername_shouldReturnUser_whenCalled() throws UsernameNotFoundException {
        FiKaUser testAppUser = new FiKaUser("1", "TestUser", "swordfish", "USER", LocalDateTime.now(),new Set[0]);
        mockRepo.save(testAppUser);
        FiKaUserResponse expectedFiKaUser = new FiKaUserResponse("1", "TestUser", "USER", new Set[0]);
        when(mockRepo.findByUsername("TestUser")).thenReturn(Optional.of(testAppUser));
        FiKaUserResponse actualUser = userService.getUserByUsername("TestUser");
        userService.loadUserByUsername("TestUser");
        assertEquals(expectedFiKaUser, actualUser);
        verify(mockRepo, times(2)).findByUsername("TestUser");
    }

    @Test
    void getUserByUsername_shouldThrowException_whenCalledWithWrongUser() throws UsernameNotFoundException {
        when(mockRepo.findByUsername("TestUser")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.getUserByUsername("TestUser"));
        verify(mockRepo).findByUsername("TestUser");
    }

    @Test
    void testLogin() {
        // Stub the behavior of SecurityContext and Authentication
        when(mockedSecurityContext.getAuthentication()).thenReturn(mockedAuthentication);
        when(mockedAuthentication.getName()).thenReturn("testUser");
        // Set the mocked SecurityContext in the SecurityContextHolder
        SecurityContextHolder.setContext(mockedSecurityContext);

        // Call the method to be tested
        String result = userService.login();

        // Verify the result
        assertEquals("testUser", result);

        // Cleanup the SecurityContextHolder to avoid side effects
        SecurityContextHolder.clearContext();
    }

    @Test
    void createNewUser_shouldReturnUser_whenCalled() throws InvalidIdException {
        FiKaUser testAppUser = new FiKaUser("1", "TestUser", "swordfish", "USER", LocalDateTime.now(),new Set[0]);
        mockRepo.save(testAppUser);
        userService.createNewUser(new RegisterUserDTO("TestUser", "swordfish"));
        verify(mockRepo).save(testAppUser);

    }

    @Test
    void createNewUser_shouldReturnException_whenCalledWithTakenUsername() throws InvalidIdException {
        FiKaUser testAppUser = new FiKaUser("1", "TestUser", "swordfish", "USER", LocalDateTime.now(),new Set[0]);
        mockRepo.save(testAppUser);
        when(mockRepo.findByUsername("TestUser")).thenReturn(Optional.of(testAppUser));
        assertThrows(InvalidIdException.class, () -> userService.createNewUser(new RegisterUserDTO("TestUser", "swordfish")));
    }

    @Test
    void addSetToUser_shouldReturnUser_whenCalledWithWrongUserId() throws InvalidIdException {
        FiKaUser testAppUser = new FiKaUser("1", "TestUser", "swordfish", "USER", LocalDateTime.now(),new Set[0]);
        mockRepo.save(testAppUser);
        SetExercise setExercise = new SetExercise("TestExercise",3,3);
        when(mockRepo.findById("1")).thenReturn(Optional.of(testAppUser));
        userService.addSetToUser("1",new Set("1","1","TestSet",new SetExercise[]{setExercise},LocalDateTime.now(),LocalDateTime.now()));
        verify(mockRepo).findById("1");
        verify(mockRepo).save(testAppUser);
    }
    @Test
    void addSetToUser_shouldReturnException_whenCalledWithTakenUserId() throws InvalidIdException {
        FiKaUser testAppUser = new FiKaUser("1", "TestUser", "swordfish", "USER", LocalDateTime.now(),new Set[0]);
        mockRepo.save(testAppUser);
        SetExercise setExercise = new SetExercise("TestExercise",3,3);
        when(mockRepo.findById("1")).thenReturn(Optional.of(testAppUser));
        assertThrows(InvalidIdException.class, () -> userService.addSetToUser("2",new Set("1","1","TestSet",new SetExercise[]{setExercise},LocalDateTime.now(),LocalDateTime.now())));
    }
    @Test
    void getUserById_shouldReturnUser_whenCalledWithTakenUserId() throws InvalidIdException {
        FiKaUser testAppUser = new FiKaUser("1", "TestUser", "swordfish", "USER", LocalDateTime.now(),new Set[0]);
        mockRepo.save(testAppUser);
        when(mockRepo.findById("1")).thenReturn(Optional.of(testAppUser));
        FiKaUser actualUser = userService.getUserById("1");
        assertEquals(actualUser,testAppUser);
        verify(mockRepo).findById("1");
    }
    @Test
    void getUserById_shouldReturnException_whenCalledWithWrongUserId() throws InvalidIdException {
        FiKaUser testAppUser = new FiKaUser("1", "TestUser", "swordfish", "USER", LocalDateTime.now(),new Set[0]);
        mockRepo.save(testAppUser);
        when(mockRepo.findById("1")).thenReturn(Optional.of(testAppUser));
        assertThrows(InvalidIdException.class, () -> userService.getUserById("2"));
    }

}