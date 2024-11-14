package com.booking.error.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorOverlapInterval {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorOverlapInterval(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

// Getters and setters
}
