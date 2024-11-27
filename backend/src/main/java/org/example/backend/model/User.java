package org.example.backend.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public record User(
        @Id
        String id,
        String userName,
        String password,
        Date createDate,
        Set[] sets
) {
}
