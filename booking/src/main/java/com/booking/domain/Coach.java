package com.booking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_COACH")
public class Coach {
    @Id
    private Integer id;
    private String area;

}
