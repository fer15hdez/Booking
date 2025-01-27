package com.booking.domain;

import java.util.List;

public record FunctionTypeDTO(
        String name,
        String description,
        List<Equipment> equipment
) {
}
