package com.boardcamp.api.dto;

public class CustomErrorResponse {
    private int status;
    private String error;
    private String message;

    public CustomErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
