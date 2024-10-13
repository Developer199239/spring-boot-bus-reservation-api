package com.example.busreservation.models;

import org.springframework.http.HttpStatus;

public class ReservationApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public ReservationApiException(HttpStatus status, String message) {
        super(message); // Ensure the message is passed to the superclass (RuntimeException)
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

    @Override
    public String getLocalizedMessage() {
        return message;
    }
}
