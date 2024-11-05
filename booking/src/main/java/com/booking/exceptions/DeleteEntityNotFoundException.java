package com.booking.exceptions;

public class DeleteEntityNotFoundException extends RuntimeException{
    public DeleteEntityNotFoundException(String message) {
        super(message);
    }
}
