package org.example.backend.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateTimeService {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
