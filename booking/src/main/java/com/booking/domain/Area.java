package com.booking.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AREA")
public class Area {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @Size(max = 500)
    private String description;
    @ManyToMany(mappedBy = "areas")
    private List<Coach> coaches;

    public Area(Integer id){
        this.id = id;
    }
}
