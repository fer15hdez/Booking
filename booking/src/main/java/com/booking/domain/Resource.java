package com.booking.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

//@Entity
//@Table(name = "T_RESOURCE")
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@SuperBuilder(toBuilder = true)
public class Resource {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 3, max = 20)
    private String name;
    @Size(max = 100)
    private String description;
    private Integer availability;
    @ManyToMany(mappedBy = "resource")
    private List<Booking> bookings;

    public Resource(){}

    public Resource(Integer id){
        this.id = id;
    }

    public Resource(Integer id, String name, String description, Integer availability, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.availability = availability;
        this.bookings = bookings;
    }

}
