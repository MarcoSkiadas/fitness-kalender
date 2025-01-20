package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.exceptions.InvalidIdException;
import org.example.backend.model.Message;
import org.example.backend.model.dto.MessageType;
import org.example.backend.repository.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepo messageRepo;
    private final IdService idService;
    private final DateTimeService dateTimeService;

    public Message sendMessage (String recipientId, String senderId, String messageContent, MessageType messageType) {
        Message newMessage = new Message(idService.generateUUID(),recipientId,senderId,messageContent,false,false,messageType,dateTimeService.now());
        messageRepo.save(newMessage);
        return newMessage;
    }
    public List<Message> getMessagesForUser(String recipientId) {
        if (messageRepo.findByRecipientId(recipientId).isEmpty()) {
            throw new InvalidIdException("Recipient Id: "+recipientId+" has not been found");
        }
        return messageRepo.findByRecipientId(recipientId);
    }

    public Message acceptMessage(String messageId) {
        Message message = messageRepo.findById(messageId).orElseThrow(() -> new InvalidIdException("Message not found"));
        return message.withAccepted(true);
    }



}
