package org.example.backend.model;

import org.example.backend.model.dto.FiKaUserResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TrainingDayTest {

    @Test
    void testEquals() {
        LocalDateTime now = LocalDateTime.now();
        Friend[] friends1 = new Friend[]{new Friend("1","TestFriend1"),new Friend("2","TestFriend2")};
        Friend[] friends2 = new Friend[]{new Friend("3","TestFriend1"),new Friend("2","TestFriend2")};

        TrainingDay trainingDay1 = new TrainingDay("1","1",friends1,now);
        TrainingDay trainingDay2 = new TrainingDay("1","1",friends1,now);

        assertEquals(trainingDay1, trainingDay2);

        TrainingDay trainingDay3 = new TrainingDay("1","1",friends2,now);

        assertNotEquals(trainingDay2, trainingDay3);
    }

    @Test
    void testHashCode() {
        LocalDateTime now = LocalDateTime.now();
        Friend[] friends1 = new Friend[]{new Friend("1","TestFriend1"),new Friend("2","TestFriend2")};
        Friend[] friends2 = new Friend[]{new Friend("3","TestFriend1"),new Friend("2","TestFriend2")};

        TrainingDay trainingDay1 = new TrainingDay("1","1",friends1,now);
        TrainingDay trainingDay2 = new TrainingDay("1","1",friends1,now);

        assertEquals(trainingDay1.hashCode(), trainingDay2.hashCode());

        TrainingDay trainingDay3 = new TrainingDay("1","1",friends2,now);

        assertNotEquals(trainingDay1.hashCode(), trainingDay3.hashCode());
    }

    @Test
    void testToString() {
        LocalDateTime trainingTime = LocalDateTime.now();

        String id = "1";
        String creatorId = "1";
        Friend[] participants = new Friend[]{new Friend("1","Test1"), new Friend("2","Test2")};

        String expected = "TrainingDay{" +
                "id='" + id + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", participants=" + Arrays.toString(participants) +
                ", trainingTime=" + trainingTime +
                '}';

        TrainingDay trainingDay = new TrainingDay(id,creatorId,participants,trainingTime);

        assertEquals(expected, trainingDay.toString());
    }
}