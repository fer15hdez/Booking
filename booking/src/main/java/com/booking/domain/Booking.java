package com.booking.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Booking {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private LocalDateTime bookingStart;
    private LocalDateTime bookingEnd;
}
