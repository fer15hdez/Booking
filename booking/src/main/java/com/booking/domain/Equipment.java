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
//@Table(name = "T_EQUIPMENT")
@PrimaryKeyJoinColumn(name = "resource_id")
public class Equipment extends Resource{

    private Integer weight;
    private Integer length;
    private Integer height;
    @ManyToMany
    @JoinTable(
            name = "equipment_function",
            joinColumns = { @JoinColumn(name = "equipment_id") },
            inverseJoinColumns = { @JoinColumn(name = "function_id") }
    )
    private List<FunctionType> function;
}
