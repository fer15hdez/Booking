package com.booking.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "AREA")
public class Area {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "areas")
    private List<Coach> coaches;
}
