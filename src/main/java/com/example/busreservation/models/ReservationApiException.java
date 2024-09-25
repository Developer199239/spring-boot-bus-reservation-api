package com.example.busreservation.models;

import org.springframework.http.HttpStatus;

public class ReservationApiException extends RuntimeException{
    private final HttpStatus status;
    private final String message;

    public ReservationApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
