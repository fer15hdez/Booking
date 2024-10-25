package com.booking.domain;

import java.util.List;

public record EquipmentDTO(

         String name,
         String description,
         Integer availability,

         Integer weight,
         Integer length,
         Integer height,
         List<FunctionType> function
) {
}
