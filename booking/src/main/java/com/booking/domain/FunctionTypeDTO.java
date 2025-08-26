package com.booking.domain;

import jakarta.validation.constraints.Size;

import java.util.List;

public record FunctionTypeDTO(
        @Size(min = 3, max = 20)
        String name,
        @Size(min = 3, max = 100)
        String description,
        List<Equipment> equipment
) {
}
