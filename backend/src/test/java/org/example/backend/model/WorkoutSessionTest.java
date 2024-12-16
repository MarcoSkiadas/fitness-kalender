package org.example.backend.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutSessionTest {

    @Test
    void testEquals() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();
        WorkoutExercise[] workoutExercises1 = new WorkoutExercise[]{new WorkoutExercise("workoutExercise1", 2,3,3), new WorkoutExercise("workoutExercise2", 3,3,3)};
        WorkoutExercise[] workoutExercises2 = new WorkoutExercise[]{new WorkoutExercise("workoutExercise3", 2,3,3), new WorkoutExercise("workoutExercise3", 3,3,3)};

        WorkoutSession workoutSession1 = new WorkoutSession("1","1",date,workoutExercises1,now);
        WorkoutSession workoutSession2 = new WorkoutSession("1","1",date,workoutExercises1,now);

        assertEquals(workoutSession1, workoutSession2);

        WorkoutSession workoutSession3 = new WorkoutSession("1","1",date,workoutExercises2,now);

        assertNotEquals(workoutSession2, workoutSession3);
    }

    @Test
    void testHashCode() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();
        WorkoutExercise[] workoutExercises1 = new WorkoutExercise[]{new WorkoutExercise("workoutExercise1", 2,3,3), new WorkoutExercise("workoutExercise2", 3,3,3)};
        WorkoutExercise[] workoutExercises2 = new WorkoutExercise[]{new WorkoutExercise("workoutExercise3", 2,3,3), new WorkoutExercise("workoutExercise3", 3,3,3)};

        WorkoutSession workoutSession1 = new WorkoutSession("1","1",date,workoutExercises1,now);
        WorkoutSession workoutSession2 = new WorkoutSession("1","1",date,workoutExercises1,now);

        assertEquals(workoutSession1.hashCode(), workoutSession2.hashCode());

        WorkoutSession workoutSession3 = new WorkoutSession("1","1",date,workoutExercises2,now);

        assertNotEquals(workoutSession1.hashCode(), workoutSession3.hashCode());
    }

    @Test
    void testToString() {


        String id = "1";
        String userId = "1";
        Date workoutDate = new Date();
        WorkoutExercise[] workoutExercises = new WorkoutExercise[]{new WorkoutExercise("workoutExercise1", 2,3,3), new WorkoutExercise("workoutExercise2", 3,3,3)};
        LocalDateTime createdAt = LocalDateTime.now();

        String expected = "WorkoutSession{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", workoutDate=" + workoutDate +
                ", workoutExercise=" + Arrays.toString(workoutExercises) +
                ", createdAt=" + createdAt +
                '}';

    WorkoutSession workoutSession = new WorkoutSession(id,userId,workoutDate,workoutExercises,createdAt);

        assertEquals(expected, workoutSession.toString());
    }
}