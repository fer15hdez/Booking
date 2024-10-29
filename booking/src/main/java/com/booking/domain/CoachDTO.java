package com.booking.domain;

import java.util.List;

public record CoachDTO(String name,
                       String description,
                       Integer availability,
                       List<Area> areas
                       ) {
}
