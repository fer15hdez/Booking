package com.booking.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JsonManagedReference
//    @JsonIgnoreProperties({"coaches", "handler", "hibernateLazyInitializer"})
    @ToString.Exclude
    private List<Coach> coaches;

    public Area(Integer id){
        this.id = id;
    }
}
