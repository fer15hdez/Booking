package com.booking.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FUNCTION_TYPE")
@Builder
public class FunctionType {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "function")
//    @Nullable
//    @JsonManagedReference
    private List<Equipment> equipment;

    public FunctionType(){
        this.equipment = new ArrayList<>();
    }

    public FunctionType(Integer id){
        this.id = id;
    }

    public FunctionType(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


