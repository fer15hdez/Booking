package com.booking.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "FUNCTION_TYPE")
public class FunctionType {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @ManyToMany
    private List<Equipment> equipment;
}
