package com.boardcamp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GameConflictException extends RuntimeException {
    public GameConflictException(String message) {
        super(message);
    }
}