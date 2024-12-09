package org.example.backend.controller;

import org.example.backend.model.Set;
import org.example.backend.model.SetExercise;
import org.example.backend.repository.SetRepo;
import org.example.backend.service.SetService;
import org.example.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
class SetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SetRepo setRepo;
    @MockBean
    private SetService setService;
    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();
        SetExercise setExercise = new SetExercise("TestExercise",3,3);
        setRepo.save(new Set("1","1","TestSet",new SetExercise[]{setExercise},now,now));
    }

    @Test
    void getSetById() {
    }

    @Test
    void getSet() {
    }

    @Test
    void createSet_shouldReturnSet_whenCalled() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/set")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "id": "1",
                        "userId": "1",
                        "name": "TestSet",
                        "exercise": [
                        {
                        "exerciseName": "TestExercise",
                         "defaultSets": 3,
                          "defaultRepetitions": 3
                         }
                                  ],
                        "createdAt": "",
                        "updatedAt": ""
                    }
                """)
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}