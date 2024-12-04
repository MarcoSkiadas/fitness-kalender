package org.example.backend.repository;

import org.example.backend.model.FiKaUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<FiKaUser, String> {

    Optional<FiKaUser> findByUsername(String username);
}
