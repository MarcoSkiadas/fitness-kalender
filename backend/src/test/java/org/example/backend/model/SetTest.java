package org.example.backend.model;

import org.example.backend.model.dto.FiKaUserResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

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
    @Test
    void testEquals() {
        LocalDateTime now = LocalDateTime.now();
        SetExercise[] setExercise1 = new SetExercise[]{new SetExercise("TestUpperMuscle", 2,2), new SetExercise("TestLowerMuscle", 2,2),};
        SetExercise[] setExercise2 = new SetExercise[]{new SetExercise("TestMiddleMuscle", 2,2), new SetExercise("TestLowerMuscle", 2,2),};


        Set set1 = new Set("1","1","TestSet",setExercise1, now,now);
        Set set2 = new Set("1","1","TestSet",setExercise1, now,now);

        assertEquals(set1, set2);

        Set set3 = new Set("1","1","TestSet",setExercise2, now,now);

        assertNotEquals(set2, set3);
    }

    @Test
    void testHashCode() {
        LocalDateTime now = LocalDateTime.now();
        SetExercise[] setExercise1 = new SetExercise[]{new SetExercise("TestUpperMuscle", 2,2), new SetExercise("TestLowerMuscle", 2,2),};
        SetExercise[] setExercise2 = new SetExercise[]{new SetExercise("TestMiddleMuscle", 2,2), new SetExercise("TestLowerMuscle", 2,2),};


        Set set1 = new Set("1","1","TestSet",setExercise1, now,now);
        Set set2 = new Set("1","1","TestSet",setExercise1, now,now);

        assertEquals(set1.hashCode(), set2.hashCode());

        Set set3 = new Set("1","1","TestSet",setExercise2, now,now);

        assertNotEquals(set1.hashCode(), set3.hashCode());
    }

    @Test
    void testToString() {
        LocalDateTime now = LocalDateTime.now();

        String id = "1";
        String userId = "1";
        String name = "swordfish";
        SetExercise[] exercise = new SetExercise[]{new SetExercise("TestUpperMuscle", 2,2), new SetExercise("TestLowerMuscle", 2,2),};

        String expected = "Set{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", exercise=" + Arrays.toString(exercise) +
                ", createdAt=" + now +
                ", updatedAt=" + now +
                '}';

        Set set1 = new Set(id,userId,name,exercise,now,now);

        assertEquals(expected, set1.toString());
    }
    }