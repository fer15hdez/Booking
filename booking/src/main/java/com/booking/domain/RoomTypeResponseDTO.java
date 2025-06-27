package com.booking.domain;

import jakarta.validation.constraints.NotBlank;


public record RoomTypeResponseDTO(
        @NotBlank
        String name,
        String description
) {
}
