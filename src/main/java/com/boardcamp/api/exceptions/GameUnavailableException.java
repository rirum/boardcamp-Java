package com.boardcamp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class GameUnavailableException extends RuntimeException {
    public GameUnavailableException(String message) {
        super(message);
    }
}
