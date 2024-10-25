package com.booking.domain;

import java.util.List;

public record EquipmentResponseDTO(
        String name,
        String description,
        Integer availability,
        List<FunctionType> function
) {
}
