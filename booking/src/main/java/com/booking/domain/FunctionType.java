package com.booking.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "FUNCTION_TYPE")
@Builder
public class FunctionType {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "function")
    private List<Equipment> equipment;
}
