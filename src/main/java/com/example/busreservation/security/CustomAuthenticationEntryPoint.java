package com.example.busreservation.security;

import com.example.busreservation.models.ErrorDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode(HttpStatus.UNAUTHORIZED.value());
        errorDetails.setErrorMessage("Unauthorized access - Please provide valid credentials");
        errorDetails.setDevErrorMessage(request.getRequestURI());
        errorDetails.setTimestamp(new Date().getTime());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Write the error details to the response
        response.getWriter().write(errorDetails.toJson());
    }
}
