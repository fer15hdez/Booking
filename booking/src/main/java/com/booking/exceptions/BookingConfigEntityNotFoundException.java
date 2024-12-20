package com.booking.exceptions;

public class BookingConfigEntityNotFoundException extends RuntimeException{
    public BookingConfigEntityNotFoundException(String message) {
        super(message);
    }
}
