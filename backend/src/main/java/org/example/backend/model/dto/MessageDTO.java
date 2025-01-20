package org.example.backend.model.dto;


public record MessageDTO(
        String recipientId,
        String senderId,
        String messageContent,
        MessageType messageType
) {
}
