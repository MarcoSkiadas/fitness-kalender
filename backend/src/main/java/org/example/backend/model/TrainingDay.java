package org.example.backend.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public record TrainingDay(
        @Id
        String id,
        String creatorId,
        Friend[] participants,
        LocalDateTime trainingTime

) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingDay that = (TrainingDay) o;
        return Objects.equals(id, that.id) && Objects.equals(creatorId, that.creatorId) && Objects.deepEquals(participants, that.participants) && Objects.equals(trainingTime, that.trainingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creatorId, Arrays.hashCode(participants), trainingTime);
    }

    @Override
    public String toString() {
        return "TrainingDay{" +
                "id='" + id + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", participants=" + Arrays.toString(participants) +
                ", trainingTime=" + trainingTime +
                '}';
    }
}
