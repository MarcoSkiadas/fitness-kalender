package org.example.backend.exeptions;

public class InvalidIdException extends RuntimeException {

    public InvalidIdException(String message) {
        super(message);
    }
}
