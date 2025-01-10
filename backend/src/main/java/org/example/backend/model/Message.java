package org.example.backend.model;

import lombok.With;
import org.example.backend.model.dto.MessageType;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@With
public record Message(
        @Id
        String id,
        String recipientId,
        String senderId,
        String messageContent,
        boolean accepted,
        boolean read,
        MessageType messageType,
        LocalDateTime createdAt

) {}
