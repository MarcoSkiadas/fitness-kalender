package org.example.backend.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {

    @Test
    void getSetId() {
            // Arrange: Erstellen eines Set-Objekts mit einer Test-ID
            String expectedId = "test-set-id";
            Set set = new Set(
                    expectedId,               // id
                    "test-user-id",          // userId
                    "Test Set Name",         // name
                    new SetExercise[]{},      // exercise
                    LocalDateTime.now(),      // createdAt
                    LocalDateTime.now()       // updatedAt
            );

            // Act: Abrufen der ID mittels getSetId()
            String actualId = set.getSetId();

            // Assert: Sicherstellen, dass die zurückgegebene ID der erwarteten entspricht
            assertEquals(expectedId, actualId, "Die ID sollte mit der erwarteten ID übereinstimmen.");
        }
    }