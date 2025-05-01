package com.booking.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@EqualsAndHashCode(callSuper = true)
@Data
//@NoArgsConstructor
//@AllArgsConstructor
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
            inverseJoinColumns = { @JoinColumn(name = "function_id") },
            uniqueConstraints = {@UniqueConstraint(columnNames = {"equipment_id", "function_id"})}
    )
    private List<FunctionType> function;

    public Equipment(){
        this.function = new ArrayList<>();
    }

    public Equipment(Integer id){
        super(id);
    }

    @Override
    public String toString() {
        return "{" +
                "weight=" + weight +
                ", length=" + length +
                ", height=" + height +
                ", function=" + function +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(getWeight(), equipment.getWeight()) &&
                Objects.equals(getLength(), equipment.getLength()) &&
                Objects.equals(getHeight(), equipment.getHeight()) &&
                Objects.equals(getFunction(), equipment.getFunction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWeight(), getLength(), getHeight(), getFunction());
    }

    public void addFunctionType(FunctionType functionType){
        this.function.add(functionType);
        functionType.getEquipment().add(this);
    }

    public void removeFunctionType(FunctionType functionType){
        this.function.remove(functionType);
        functionType.getEquipment().remove(this);
    }

}
