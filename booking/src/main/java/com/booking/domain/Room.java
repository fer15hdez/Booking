package com.booking.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "T_ROOM")
public class Room extends Resource{
    @Id
    @GeneratedValue
    private Integer id;
    private Integer width;
    private Integer length;
    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonBackReference
    private String type;
}
