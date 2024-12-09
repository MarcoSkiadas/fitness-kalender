package org.example.backend.model.dto;

import org.example.backend.model.Set;
import org.example.backend.model.SetExercise;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FiKaUserResponseTest {

    @Test
    void fromAppUser() {
    }

    @Test
    void testEquals() {
        LocalDateTime date = LocalDateTime.now();
        Set[] sets1 = new Set[]{new Set("1", "2","Test1",new SetExercise[0], date,date), new Set("2", "3","Test2",new SetExercise[0], date,date)};
        Set[] sets2 = new Set[]{new Set("3", "2","Test1",new SetExercise[0], date,date), new Set("2", "3","Test2",new SetExercise[0], date,date)};

        FiKaUserResponse fiKaUser1 = new FiKaUserResponse("1","Testuser","User",sets1);
        FiKaUserResponse fiKaUser2 = new FiKaUserResponse("1","Testuser","User",sets1);

        assertEquals(fiKaUser1, fiKaUser2);

        FiKaUserResponse fiKaUser3 = new FiKaUserResponse("1","Testuser","User",sets2);

        assertNotEquals(fiKaUser2, fiKaUser3);
    }

    @Test
    void testHashCode() {
        LocalDateTime date = LocalDateTime.now();
        Set[] sets1 = new Set[]{new Set("1", "2","Test1",new SetExercise[0], date,date), new Set("2", "3","Test2",new SetExercise[0], date,date)};
        Set[] sets2 = new Set[]{new Set("3", "2","Test1",new SetExercise[0], date,date), new Set("2", "3","Test2",new SetExercise[0], date,date)};

        FiKaUserResponse fiKaUser1 = new FiKaUserResponse("1","Testuser","User",sets1);
        FiKaUserResponse fiKaUser2 = new FiKaUserResponse("1","Testuser","User",sets1);

        assertEquals(fiKaUser1.hashCode(), fiKaUser2.hashCode());

        FiKaUserResponse fiKaUser3 = new FiKaUserResponse("1","Testuser","User",sets2);

        assertNotEquals(fiKaUser1.hashCode(), fiKaUser3.hashCode());
    }

    @Test
    void testToString() {

        LocalDateTime date = LocalDateTime.now();

        String id = "1";
        String username = "Testuser";
        String role = "User";
        Set[] sets1 = new Set[]{new Set("1", "2","Test1",new SetExercise[0], date,date), new Set("2", "3","Test2",new SetExercise[0], date,date)};

        String expected = "FiKaUserResponse{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", sets=" + Arrays.toString(sets1) +
                '}';

        FiKaUserResponse fiKaUser1 = new FiKaUserResponse(id,username,role,sets1);

        assertEquals(expected, fiKaUser1.toString());
    }
}