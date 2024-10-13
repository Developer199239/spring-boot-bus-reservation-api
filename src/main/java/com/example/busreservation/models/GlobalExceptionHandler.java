package com.example.busreservation.models;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservationApiException.class)
    public ResponseEntity<ErrorDetails> handleReservationApiException(
            ReservationApiException exception,
            WebRequest request
    ) {
        final ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode(exception.getStatus().value());
        errorDetails.setErrorMessage(exception.getLocalizedMessage());
        errorDetails.setDevErrorMessage(request.getDescription(false));
        errorDetails.setTimestamp(System.currentTimeMillis());
        System.out.print("========enter1");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(
            AccessDeniedException exception,
            WebRequest request
    ) {
        final ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        errorDetails.setErrorMessage("Access denied. You are not authorized to access this resource.");
        errorDetails.setDevErrorMessage(request.getDescription(false));
        errorDetails.setTimestamp(System.currentTimeMillis());
        System.out.print("========enter2");
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    // Handle DataIntegrityViolationException (e.g., duplicate entry error)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception,
            WebRequest request
    ) {
        final ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage("Duplicate entry or constraint violation occurred.");
        errorDetails.setDevErrorMessage(request.getDescription(false));
        errorDetails.setTimestamp(System.currentTimeMillis());
        System.out.print("========enter3");
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT); // Use HTTP 409 Conflict
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
            Exception exception,
            WebRequest request
    ) {
        final ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage(exception.getLocalizedMessage());
        errorDetails.setDevErrorMessage(request.getDescription(false));
        errorDetails.setTimestamp(System.currentTimeMillis());
        System.out.print("========enter4");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
