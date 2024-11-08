package com.booking.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "booking_resource",
            joinColumns = { @JoinColumn(name = "resource_id") },
            inverseJoinColumns = { @JoinColumn(name = "booking_id") }
    )
    private List<Resource> resource;
    @ManyToMany
    @JoinTable(
            name = "booking_customer",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "booking_id") }
    )
    private List<Customer> customers;
    private LocalDateTime bookingStart;
    private LocalDateTime bookingEnd;
}
