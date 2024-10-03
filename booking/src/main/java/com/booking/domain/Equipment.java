package com.booking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_EQUIPMENT")
public class Equipment extends Resource{
    @Id
    private Integer id;
    private Integer weight;
    private Integer length;
    private Integer height;
    private String function;
}
