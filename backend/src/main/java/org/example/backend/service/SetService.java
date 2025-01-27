package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.exceptions.InvalidIdException;
import org.example.backend.model.FiKaUser;
import org.example.backend.model.Set;
import org.example.backend.repository.SetRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    public void updateSet(Set updatedSet,String setId,String userId) {
        FiKaUser fiKaUser = userService.getUserById(userId);

        Set[] updatedSets = Arrays.stream(fiKaUser.sets())
                .map(existingSet -> existingSet != null && setId.equals(existingSet.getSetId())
                        ? updatedSet // Ersetze das Set, wenn die IDs übereinstimmen
                        : existingSet) // Behalte das bestehende Set, wenn keine Übereinstimmung
                .toArray(Set[]::new); // Konvertiere den Stream zurück in ein Array

        userService.saveUser(fiKaUser.withSets(updatedSets));

    }

    public void deleteSet(String setId) {
        if (!setRepo.existsById(setId)) {
            throw new InvalidIdException("Set not found");
        }
        setRepo.deleteById(setId);
    }
}
