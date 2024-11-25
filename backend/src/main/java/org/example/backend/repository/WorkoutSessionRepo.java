package org.example.backend.repository;

import org.example.backend.model.WorkoutSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkoutSessionRepo extends MongoRepository<WorkoutSession, String> {
}
