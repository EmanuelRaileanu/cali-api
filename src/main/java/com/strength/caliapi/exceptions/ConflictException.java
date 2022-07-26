package com.strength.caliapi.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends HttpException {
    public ConflictException() {
        super("Conflict", HttpStatus.CONFLICT);
    }

    public ConflictException(String message) {
        super("Conflict: " + message, HttpStatus.CONFLICT);
    }
}
