package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.Set;
import org.example.backend.repository.SetRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SetService {
    private final SetRepo setRepo;
    private final IdService idService;
    private final DateTimeService dateTimeService;
    private final UserService userService;


    public Set getSetById(String setId) {
        return setRepo.findById(setId)
                .orElseThrow(() -> new RuntimeException("Set not found"));
    }

    public List<Set> getSet() {
        return setRepo.findAll();
    }

    public Set createSet(Set set) {
    Set newSet = new Set(idService.generateUUID(), set.userId(), set.name(), set.exercise(), dateTimeService.now(), dateTimeService.now());
        userService.addSetToUser(set.userId(),newSet);
        return setRepo.save(newSet);
    }

}
