package com.booking.domain;

public record RoomDTO(
        String name,
        String description,
        Integer availability,

        Integer width,
        Integer length,
        Integer type
                      ) {
}
