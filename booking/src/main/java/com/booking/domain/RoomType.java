package com.booking.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROOM_TYPE")
@Builder
public class RoomType {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    private String name;
    private String description;
    @OneToMany(
            mappedBy = "type",
            cascade = CascadeType.ALL
//            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Room> rooms;

    public RoomType(Integer id){
        this.id = id;
    }

}
