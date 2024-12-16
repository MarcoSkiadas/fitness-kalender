package org.example.backend.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public record WorkoutSession(
        @Id
        String id,
        String userId,
        Date workoutDate,
        WorkoutExercise[] workoutExercise,
        LocalDateTime createdAt
) {
        @Override
        public String toString() {
                return "WorkoutSession{" +
                        "id='" + id + '\'' +
                        ", userId='" + userId + '\'' +
                        ", workoutDate=" + workoutDate +
                        ", workoutExercise=" + Arrays.toString(workoutExercise) +
                        ", createdAt=" + createdAt +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                WorkoutSession that = (WorkoutSession) o;
                return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(workoutDate, that.workoutDate) && Objects.equals(createdAt, that.createdAt) && Objects.deepEquals(workoutExercise, that.workoutExercise);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, userId, workoutDate, Arrays.hashCode(workoutExercise), createdAt);
        }
}
