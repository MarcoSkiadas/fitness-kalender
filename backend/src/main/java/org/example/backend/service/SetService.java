package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.Set;
import org.example.backend.repository.SetRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SetService {
    private final SetRepo setRepo;
    private final IdService idService;


    public Set getSetById(String setId) {
        return setRepo.findById(setId)
                .orElseThrow(() -> new RuntimeException("Set not found"));
    }

    public List<Set> getSet() {
        return setRepo.findAll();
    }

    public Set createSet(Set set) {
    Set newSet = new Set(idService.generateUUID(), set.userId(), set.name(), set.exercise(), LocalDateTime.now(), LocalDateTime.now());
        return setRepo.save(newSet);
    }

}
