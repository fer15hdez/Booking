package com.booking.domain;

import java.util.List;

public record CustomerDTO(
        String name,
        List<Booking> bookings
) {
}
