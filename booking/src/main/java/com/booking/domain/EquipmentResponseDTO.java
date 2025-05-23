package com.booking.domain;

import java.util.List;

public record EquipmentResponseDTO(
        String name,
        String description,
        Integer availability,
        String function
) {
}
