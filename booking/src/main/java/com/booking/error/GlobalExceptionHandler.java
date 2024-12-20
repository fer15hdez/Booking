package com.booking.error;

import com.booking.error.pojo.ErrorDetails;
import com.booking.error.pojo.ErrorOverlapInterval;
import com.booking.exceptions.BookingConfigEntityNotFoundException;
import com.booking.exceptions.DeleteEntityNotFoundException;
import com.booking.exceptions.InvalidIntervalException;
import com.booking.exceptions.OverlapIntervalException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}

