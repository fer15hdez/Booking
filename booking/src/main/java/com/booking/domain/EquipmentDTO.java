package com.booking.domain;

import jakarta.validation.constraints.Size;

import java.util.List;

public record EquipmentDTO(

        @Size(min = 3, max = 20)
         String name,
         @Size(max = 50)
         String description,
         Integer availability,


         Integer weight,
         Integer length,
         Integer height,
         List<FunctionType> function
) {
}
