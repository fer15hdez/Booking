package com.booking.domain;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RoomTypeDTO(
        @NotBlank
        String name,
        String description,
        List<Room> rooms
) {
}
