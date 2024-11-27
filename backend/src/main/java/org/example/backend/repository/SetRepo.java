package org.example.backend.repository;

import org.example.backend.model.Set;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SetRepo extends MongoRepository<Set, String> {
}
