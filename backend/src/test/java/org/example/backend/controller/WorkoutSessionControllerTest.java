package org.example.backend.controller;


import org.example.backend.model.WorkoutExercise;
import org.example.backend.model.WorkoutSession;
import org.example.backend.repository.WorkoutSessionRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Date;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class WorkoutSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WorkoutSessionRepo workoutSessionRepo;

    @BeforeEach
    void setUp() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();
        WorkoutExercise[] workoutExercises = new WorkoutExercise[]{new WorkoutExercise("workoutExercise1", 2,3,3), new WorkoutExercise("workoutExercise2", 3,3,3)};
        workoutSessionRepo.save(new WorkoutSession("1","1",date,workoutExercises,now));
    }

    @Test
    void getWorkoutSessionById_shouldReturnWorkoutSession_whenCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/workoutSession/1")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
 {
                                     "id": "1",
                                     "userId": "1",
                                      "workoutExercise": [
                                         {
                                             "exerciseName": "workoutExercise1",
                                             "sets": 2,
                                             "repetitions": 3,
                                             "weight": 3
                                         },
                                         {
                                             "exerciseName": "workoutExercise2",
                                             "sets": 3,
                                             "repetitions": 3,
                                             "weight": 3
                                         }
                                     ]
                                 }
"""))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.workoutDate").isNotEmpty());
    }

    @Test
    void getWorkoutSession_shouldReturnAllWorkoutSession_whenCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/workoutSession")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
 [
 {
                                     "id": "1",
                                     "userId": "1",
                                      "workoutExercise": [
                                         {
                                             "exerciseName": "workoutExercise1",
                                             "sets": 2,
                                             "repetitions": 3,
                                             "weight": 3
                                         },
                                         {
                                             "exerciseName": "workoutExercise2",
                                             "sets": 3,
                                             "repetitions": 3,
                                             "weight": 3
                                         }
                                     ]
                                 }
                                 ]
"""));
    }

    @Test
    void createWorkoutSession_shouldReturnWorkoutSession_whenCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/workoutSession")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                   {
                                     "_id": "1",
                                     "userId": "1",
                                     "workoutDate": "2024-12-13T18:36:29.604+00:00",
                                      "workoutExercise": [
                                         {
                                             "exerciseName": "workoutExercise1",
                                             "sets": 2,
                                             "repetitions": 3,
                                             "weight": 3
                                         },
                                         {
                                             "exerciseName": "workoutExercise2",
                                             "sets": 3,
                                             "repetitions": 3,
                                             "weight": 3
                                         }
                                     ],
                                     "createdAt": ""
                                 }
                """)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("""
 {
                                     "userId": "1",
                                      "workoutExercise": [
                                         {
                                             "exerciseName": "workoutExercise1",
                                             "sets": 2,
                                             "repetitions": 3,
                                             "weight": 3
                                         },
                                         {
                                             "exerciseName": "workoutExercise2",
                                             "sets": 3,
                                             "repetitions": 3,
                                             "weight": 3
                                         }
                                     ]
                                 }
"""))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.workoutDate").isNotEmpty());
    }
    @Test
    void getWorkoutSessionByUserId_shouldReturnWorkoutSession_whenCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/workoutSession/user/1")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
 [
 {
                                     "id": "1",
                                     "userId": "1",
                                      "workoutExercise": [
                                         {
                                             "exerciseName": "workoutExercise1",
                                             "sets": 2,
                                             "repetitions": 3,
                                             "weight": 3
                                         },
                                         {
                                             "exerciseName": "workoutExercise2",
                                             "sets": 3,
                                             "repetitions": 3,
                                             "weight": 3
                                         }
                                     ]
                                 }
                                 ]
"""));
    }
    @Test
    void deleteWorkoutSession_shouldDeleteWorkoutSession_whenCalledWithCorrectWorkoutSessionId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/workoutSession/delete/1")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}