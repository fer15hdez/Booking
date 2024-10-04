package com.booking.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ROOM_TYPE")
public class RoomType {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(
            mappedBy = "type",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Room> rooms;
}
