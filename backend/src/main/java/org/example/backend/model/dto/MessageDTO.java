package org.example.backend.model.dto;

import java.time.LocalDateTime;

public record MessageDTO(
        String recipientId,
        String senderId,
        String messageContent,
        MessageType messageType
) {
}
