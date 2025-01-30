package org.example.backend.controller;

import org.example.backend.model.Friend;
import org.example.backend.model.Set;
import org.example.backend.model.SetExercise;
import org.example.backend.model.TrainingDay;
import org.example.backend.repository.SetRepo;
import org.example.backend.repository.TrainingDayRepo;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TrainingDayControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TrainingDayRepo trainingDayRepo;

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();
        Friend[] participants = new Friend[]{new Friend("1","TestFriend1"),new Friend("2","TestFriend2")};
        trainingDayRepo.save(new TrainingDay("1","1",participants,now));
    }
    @Test
    void createTrainingDay_shouldReturnTrainingDay_whenCalledInitially() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/trainingDay")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "id": "1",
                        "creatorId": "1",
                        "participants":[
                        {
                        "id": "1",
                         "username": "TestFriend1"
                         },
                         {
                        "id": "2",
                         "username": "TestFriend2"
                         }
                                  ],
                        "trainingTime": "2025-01-30T14:45:00Z"
                    }
                """)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}