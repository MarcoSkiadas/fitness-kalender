package org.example.backend.model.dto;

import org.example.backend.model.FiKaUser;
import org.example.backend.model.Set;

public record FiKaUserResponse(
    String id,
    String username,
    String role,
    Set[] sets

) {
        public static FiKaUserResponse fromAppUser(FiKaUser fiKaUser) {
            return new FiKaUserResponse(fiKaUser.id(), fiKaUser.username(), fiKaUser.role(), fiKaUser.sets());
        }
    }
