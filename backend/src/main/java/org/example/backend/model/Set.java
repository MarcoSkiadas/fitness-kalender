package org.example.backend.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public record Set(
        @Id
        String id,
        String userId,
        String name,
        SetExercise[] exercise,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
        public String getSetId() {
                return id;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Set set = (Set) o;
                return Objects.equals(id, set.id) && Objects.equals(name, set.name) && Objects.equals(userId, set.userId) && Objects.deepEquals(exercise, set.exercise) && Objects.equals(createdAt, set.createdAt) && Objects.equals(updatedAt, set.updatedAt);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, userId, name, Arrays.hashCode(exercise), createdAt, updatedAt);
        }

        @Override
        public String toString() {
                return "Set{" +
                        "id='" + id + '\'' +
                        ", userId='" + userId + '\'' +
                        ", name='" + name + '\'' +
                        ", exercise=" + Arrays.toString(exercise) +
                        ", createdAt=" + createdAt +
                        ", updatedAt=" + updatedAt +
                        '}';
        }
}
