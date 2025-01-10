package org.example.backend.model.dto;

public enum MessageType {
    REQUEST("Request"),
    MESSAGE("Message"),
    ANNOUNCEMENT("Announcement");

    private final String name;

    private MessageType(String name) {
        this.name = name;
    }
}
