package com.boardcamp.api.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeValueException extends RuntimeException {
    public NegativeValueException(String message) {
        super(message);
    }
}




