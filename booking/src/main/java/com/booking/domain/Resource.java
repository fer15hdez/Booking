package com.booking.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

//@Entity
//@Table(name = "T_RESOURCE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@SuperBuilder(toBuilder = true)
abstract public class Resource {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private Integer availability;


}
