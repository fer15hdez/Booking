package com.booking.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CoachDTO(
        @NotNull
        @Size(min = 3, max = 20)
        String name,
        @Size(max = 100)
        String description,
        Integer availability,
        List<Area> areas
                       ) {
}
