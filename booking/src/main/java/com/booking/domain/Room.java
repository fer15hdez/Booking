package com.booking.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
//@Table(name = "T_ROOM")
public class Room extends Resource{
    @Id
    @GeneratedValue
    private Integer id;
    private Integer width;
    private Integer length;
    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonBackReference
    private RoomType type;
}
