package org.example.backend.repository;

import org.example.backend.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepo extends MongoRepository<Message, String> {
    List<Message> findByRecipientId(String recipientId);
}
