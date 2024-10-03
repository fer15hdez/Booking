package com.booking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_ROOM")
public class Room extends Resource{
    @Id
    private Integer id;
    private Integer width;
    private Integer length;
    private String type;
}
