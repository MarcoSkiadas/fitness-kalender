package org.example.backend.model.dto;

import org.example.backend.model.FiKaUser;
import org.example.backend.model.Friend;
import org.example.backend.model.Set;

import java.util.Arrays;
import java.util.Objects;

public record FiKaUserResponse(
    String id,
    String username,
    String role,
    Set[] sets,
    Friend[] friends

) {
        public static FiKaUserResponse fromAppUser(FiKaUser fiKaUser) {
            return new FiKaUserResponse(fiKaUser.id(), fiKaUser.username(), fiKaUser.role(), fiKaUser.sets(), fiKaUser.friends());
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiKaUserResponse that = (FiKaUserResponse) o;
        return Objects.equals(id, that.id) && Objects.deepEquals(sets, that.sets) && Objects.equals(role, that.role) && Objects.equals(username, that.username) && Arrays.equals(sets, that.sets) && Arrays.equals(friends, that.friends);
    }

    @Override
    public String toString() {
        return "FiKaUserResponse{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", sets=" + Arrays.toString(sets) +
                ", friends=" + Arrays.toString(friends) +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, role, Arrays.hashCode(sets), Arrays.hashCode(friends));
    }
}
