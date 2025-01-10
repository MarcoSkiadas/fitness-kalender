package org.example.backend.controller;

import org.example.backend.model.Message;
import org.example.backend.model.dto.MessageType;
import org.example.backend.repository.MessageRepo;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MessageRepo messageRepo;

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();
        messageRepo.save(new Message("1","1","2","TestMessage1", false,false,MessageType.MESSAGE,now));
        messageRepo.save(new Message("2","1","2","TestMessage2",false,false,MessageType.MESSAGE,now));
    }

    @Test
    void sendMessage_shouldReturnMessage_whenCalledInitially() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
{
                                     "recipientId": "1",
                                     "senderId": "2",
                                     "messageContent": "TestMessage1",
                                     "messageType": "MESSAGE"
                                 }
                """)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
{
                                     "recipientId": "1",
                                     "senderId": "2",
                                     "messageContent": "TestMessage1",
                                     "accepted": false,
                                     "read": false,
                                     "messageType": "MESSAGE"
                                 }
"""))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.createdAt").isNotEmpty());

    }

    @Test
    void getMessagesForUser_ShouldReturnMessagesForUser_whenCalledWithCorrectRecipientId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/message/1")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
 [{
                                     "id": "1",
                                     "recipientId": "1",
                                     "senderId": "2",
                                     "messageContent": "TestMessage1",
                                     "accepted": false,
                                     "read": false,
                                     "messageType": "MESSAGE"
                                 },{
                                     "id": "2",
                                     "recipientId": "1",
                                     "senderId": "2",
                                     "messageContent": "TestMessage2",
                                     "accepted": false,
                                     "read": false,
                                     "messageType": "MESSAGE"
                                 }
                                 ]
"""));
    }

    @Test
    void acceptMessage_shouldReturnTrue_whenCalledWithCorrectMessageId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/message/1")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().json("""
 {
                                     "recipientId": "1",
                                     "senderId": "2",
                                     "messageContent": "TestMessage1",
                                     "accepted": true,
                                     "read": false,
                                     "messageType": "MESSAGE"
                                 }
"""))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.createdAt").isNotEmpty());
    }
}
