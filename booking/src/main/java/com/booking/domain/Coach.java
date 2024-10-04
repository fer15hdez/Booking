package com.booking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_COACH")
public class Coach extends Resource {
    @Id
    @GeneratedValue
    private Integer id;
    private String area;

}
