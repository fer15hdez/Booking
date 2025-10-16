package com.booking.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@ToString(callSuper = true)
//@Table(name = "T_COACH")
@PrimaryKeyJoinColumn(name = "resource_id")
public class Coach extends Resource {
    @ManyToMany
    @JoinTable(
            name = "coach_area",
            joinColumns = { @JoinColumn(name = "area_id") },
            inverseJoinColumns = { @JoinColumn(name = "coach_id") }
    )
    @JsonBackReference
    @ToString.Exclude
    private List<Area> areas;

    Coach(Integer id){
        super(id);
    }

}
