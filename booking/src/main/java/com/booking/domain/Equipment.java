package com.booking.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "T_EQUIPMENT")
public class Equipment extends Resource{
    @Id
    @GeneratedValue
    private Integer id;
    private Integer weight;
    private Integer length;
    private Integer height;
    @ManyToMany
    @JoinTable(
            name = "equipment_function",
            joinColumns = {@JoinColumn(name = "equipment_id")},
            inverseJoinColumns = {@JoinColumn(name = "function_id")}
    )
    private List<FunctionType> function;
}
