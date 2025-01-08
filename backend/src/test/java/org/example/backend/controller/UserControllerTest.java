package org.example.backend.controller;

import org.example.backend.model.FiKaUser;
import org.example.backend.model.Friend;
import org.example.backend.model.Set;
import org.example.backend.repository.UserRepo;
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
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();
        userRepo.save(new FiKaUser("1","testUser1","123","USER",now,new Set[0],new Friend[0]));
        userRepo.save(new FiKaUser("2","testUser2","123","USER",now,new Set[0],new Friend[0]));
    }

    @Test
    void createUser_shouldReturnCreated_whenCalledWithNewUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "password": "123",
                        "username": "testUser"
                    }
                """)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    void addFriend_shouldReturnCreated_whenCalledWithFriendId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/friend/1/2")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}