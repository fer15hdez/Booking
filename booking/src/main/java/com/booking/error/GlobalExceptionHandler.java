package com.booking.error;

import com.booking.error.pojo.ErrorDetails;
import com.booking.error.pojo.ErrorOverlapInterval;
import com.booking.exceptions.BookingConfigEntityNotFoundException;
import com.booking.exceptions.DeleteEntityNotFoundException;
import com.booking.exceptions.InvalidIntervalException;
import com.booking.exceptions.OverlapIntervalException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());

        return errorResponse;
    }

    @ExceptionHandler(DeleteEntityNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(DeleteEntityNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OverlapIntervalException.class)
    public ResponseEntity<?> overlaptIntervalException(OverlapIntervalException ex, WebRequest request) {
        ErrorOverlapInterval errorOverlapInterval = new ErrorOverlapInterval(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorOverlapInterval, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidIntervalException.class)
    public ResponseEntity<?> invalidIntervalException(InvalidIntervalException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingConfigEntityNotFoundException.class)
    public ResponseEntity<?> bookingConfigEntityNotFoundException(BookingConfigEntityNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handlerConstraintViolationException(
            ConstraintViolationException ex, WebRequest request){

        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(fieldName, message);
        });


        // Personaliza el mensaje general si lo deseas
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Error de validación en los datos enviados.");
        response.put("errors", errors);
        response.put("timestamp", java.time.LocalDateTime.now());
        response.put("details", "Verifique los campos con errores.");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}

