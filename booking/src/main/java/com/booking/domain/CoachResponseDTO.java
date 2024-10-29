package com.booking.domain;

import java.util.List;

public record CoachResponseDTO(String name,
                               String description,
                               Integer availability,
                               List<Area> areas) {
}
