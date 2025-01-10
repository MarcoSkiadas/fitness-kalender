package org.example.backend.service;

import org.example.backend.exceptions.InvalidIdException;
import org.example.backend.model.Message;
import org.example.backend.model.dto.MessageType;
import org.example.backend.repository.MessageRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    private final MessageRepo mockRepo = mock(MessageRepo.class);
   private final IdService mockIdService = mock(IdService.class);
    private final DateTimeService mockDateTimeService = mock(DateTimeService.class);
    private final MessageService messageService = new MessageService(mockRepo, mockIdService, mockDateTimeService);

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();
        Message newMessage = new Message("1","1","2","test",false,false, MessageType.MESSAGE,now);
        mockRepo.save(newMessage);

    }
    @Test
    void sendMessage_shouldReturnMessage_WhenCalledInitially() {
        LocalDateTime now = LocalDateTime.now();
        Message expectedMessage = new Message("1","1","1","test",false,false, MessageType.MESSAGE,now);
        when(mockRepo.save(expectedMessage)).thenReturn(expectedMessage);
        when(mockDateTimeService.now()).thenReturn(now);
        when(mockIdService.generateUUID()).thenReturn("1");
        Message message = messageService.sendMessage("1","1","test",MessageType.MESSAGE);
        assertEquals(expectedMessage, message);
        verify(mockRepo, times(1)).save(message);

    }

    @Test
    void getMessagesForUser_ShouldReturnMessagesForUser_WhenCalledWithRightRecipientId() {
        LocalDateTime now = LocalDateTime.now();
        Message message1 = new Message("1","1","2","test",false,false, MessageType.MESSAGE,now);
        Message message2 = new Message("1","1","2","test",true,false, MessageType.MESSAGE,now);
        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);
        when(mockRepo.findByRecipientId("1")).thenReturn(messages);
        List<Message> messagesForUser = messageService.getMessagesForUser("1");
        assertEquals(messagesForUser, messages);
        verify(mockRepo, times(2)).findByRecipientId("1");
    }
    @Test
    void getMessagesForUser_ShouldReturnExceptions_WhenCalledWithWrongRecipientId() {
        when(mockRepo.findByRecipientId("2")).thenReturn(Collections.emptyList());
        assertThrows(InvalidIdException.class, () -> messageService.getMessagesForUser("2"));
    }

    @Test
    void acceptMessage_shouldReturnTrue_whenCalled() {
        LocalDateTime now = LocalDateTime.now();
        Message message = new Message("1","1","2","test",false,false, MessageType.MESSAGE,now);
        Message expectedMessage = new Message("1","1","2","test",true,false, MessageType.MESSAGE,now);
        when(mockRepo.findById("1")).thenReturn(Optional.of(message));
        message = messageService.acceptMessage("1");
        assertEquals(expectedMessage, message);
        verify(mockRepo, times(1)).findById("1");
    }
    @Test
    void acceptMessage_shouldReturnException_whenCalledWithWrongId() {
      when(mockRepo.findById("1")).thenReturn(Optional.empty());
      assertThrows(InvalidIdException.class, () -> messageService.acceptMessage("1"));
    }

}