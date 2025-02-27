package org.example.backend.model;

import lombok.With;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@With
public record FiKaUser(
        @Id
        String id,
        String username,
        String password,
        String role,
        LocalDateTime createDate,
        Set[] sets,
        Friend[] friends
) {
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                FiKaUser fiKaUser = (FiKaUser) o;
                return Objects.equals(id, fiKaUser.id) && Objects.deepEquals(sets, fiKaUser.sets) && Objects.equals(role, fiKaUser.role) && Objects.equals(username, fiKaUser.username) && Objects.equals(password, fiKaUser.password) && Objects.equals(createDate, fiKaUser.createDate) && Arrays.deepEquals(friends, fiKaUser.friends);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, username, password, role, createDate, Arrays.hashCode(sets), Arrays.hashCode(friends));
        }

        @Override
        public String toString() {
                return "FiKaUser{" +
                        "id='" + id + '\'' +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", role='" + role + '\'' +
                        ", createDate=" + createDate +
                        ", sets=" + Arrays.toString(sets) +
                        ", friends=" + Arrays.toString(friends) +
                        '}';
        }
        public FiKaUser deleteSetById(String setId) {
                Set[] updatedSets = Arrays.stream(sets)
                        .filter(set -> !Objects.equals(set.id(), setId))
                        .toArray(Set[]::new);
                return this.withSets(updatedSets);
        }
}
