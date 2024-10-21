package com.booking.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
//@Table(name = "T_COACH")
@PrimaryKeyJoinColumn(name = "resource_id")
public class Coach extends Resource {
    private String area;
    @ManyToMany
    @JoinTable(
            name = "coach_area",
            joinColumns = { @JoinColumn(name = "area_id") },
            inverseJoinColumns = { @JoinColumn(name = "coach_id") }
    )
    private List<Area> areas;

}
