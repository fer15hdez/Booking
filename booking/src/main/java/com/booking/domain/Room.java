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
@PrimaryKeyJoinColumn(name = "resource_id")
public class Room extends Resource{

    private Integer width;
    private Integer length;
    @ManyToOne
    @JoinColumn(name = "type_id")
//    @JsonBackReference
    private RoomType type;
}
