package org.example.backend.model;

import org.example.backend.model.dto.FiKaUserResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FiKaUserTest {

    @Test
    void testEquals() {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime now = LocalDateTime.now();
        Set[] sets1 = new Set[]{new Set("1", "2","Test1",new SetExercise[0], date,date), new Set("2", "3","Test2",new SetExercise[0], date,date)};
        Set[] sets2 = new Set[]{new Set("3", "2","Test1",new SetExercise[0], date,date), new Set("2", "3","Test2",new SetExercise[0], date,date)};
        Friend[] friends1 = new Friend[]{new Friend("1","TestFriend1"),new Friend("2","TestFriend2")};
        Friend[] friends2 = new Friend[]{new Friend("3","TestFriend1"),new Friend("2","TestFriend2")};

        FiKaUserResponse fiKaUser1 = new FiKaUserResponse("1","Testuser","User",sets1, friends1);
        FiKaUserResponse fiKaUser2 = new FiKaUserResponse("1","Testuser","User",sets1, friends1);

        assertEquals(fiKaUser1, fiKaUser2);

        FiKaUser fiKaUser3 = new FiKaUser("1","Testuser","swordfish","User",now,sets2, friends2);

        assertNotEquals(fiKaUser2, fiKaUser3);
    }

    @Test
    void testHashCode() {
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime now = LocalDateTime.now();
        Set[] sets1 = new Set[]{new Set("1", "2","Test1",new SetExercise[0], date,date), new Set("2", "3","Test2",new SetExercise[0], date,date)};
        Set[] sets2 = new Set[]{new Set("3", "2","Test1",new SetExercise[0], date,date), new Set("2", "3","Test2",new SetExercise[0], date,date)};
        Friend[] friends1 = new Friend[]{new Friend("1","TestFriend1"),new Friend("2","TestFriend2")};
        Friend[] friends2 = new Friend[]{new Friend("3","TestFriend1"),new Friend("2","TestFriend2")};

        FiKaUserResponse fiKaUser1 = new FiKaUserResponse("1","Testuser","User",sets1, friends1);
        FiKaUserResponse fiKaUser2 = new FiKaUserResponse("1","Testuser","User",sets1, friends1);

        assertEquals(fiKaUser1.hashCode(), fiKaUser2.hashCode());

        FiKaUser fiKaUser3 = new FiKaUser("1","Testuser","swordfish","User",now,sets2, friends2);

        assertNotEquals(fiKaUser1.hashCode(), fiKaUser3.hashCode());
    }

    @Test
    void testToString() {

        LocalDateTime date = LocalDateTime.now();
        LocalDateTime now = LocalDateTime.now();

        String id = "1";
        String username = "Testuser";
        String password = "swordfish";
        String role = "User";
        Set[] sets1 = new Set[]{new Set("1", "2","Test1",new SetExercise[0], date,date), new Set("2", "3","Test2",new SetExercise[0], date,date)};
        Friend[] friends = new Friend[]{new Friend("1","Test1"), new Friend("2","Test2")};

        String expected = "FiKaUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", createDate=" + now +
                ", sets=" + Arrays.toString(sets1) +
                ", friends=" + Arrays.toString(friends) +
                '}';

        FiKaUser fiKaUser1 = new FiKaUser(id,username,password,role,now,sets1,friends);

        assertEquals(expected, fiKaUser1.toString());
    }
}