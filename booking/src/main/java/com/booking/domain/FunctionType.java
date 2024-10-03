package com.booking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FUNCTION_TYPE")
public class FunctionType {
    @Id
    private Integer id;
    private String name;
    private String description;
}
