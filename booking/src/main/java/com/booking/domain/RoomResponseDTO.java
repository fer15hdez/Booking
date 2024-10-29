package com.booking.domain;

public record RoomResponseDTO(
        String name,
        String description,

        Integer width,
        Integer length,
        RoomType type
) {
}
