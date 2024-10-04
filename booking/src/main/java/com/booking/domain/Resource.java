package com.booking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_RESOURCE")
public class Resource {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private Integer availability;


}
