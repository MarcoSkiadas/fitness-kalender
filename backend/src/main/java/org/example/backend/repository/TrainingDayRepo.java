package org.example.backend.repository;
import org.example.backend.model.TrainingDay;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainingDayRepo extends MongoRepository<TrainingDay, String> {
}
