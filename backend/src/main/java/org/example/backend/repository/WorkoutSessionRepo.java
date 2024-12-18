package org.example.backend.repository;

import org.example.backend.model.WorkoutSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkoutSessionRepo extends MongoRepository<WorkoutSession, String> {
    List<WorkoutSession> findByUserId(String userId);
}
