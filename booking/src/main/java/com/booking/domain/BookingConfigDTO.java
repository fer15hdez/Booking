package com.booking.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BookingConfigDTO(
         LocalDateTime lowerLimitInterval,
         LocalDateTime upperLimitInterval,
         Long minTimeReservation, // En minutos
         Long maxTimeReservation // En minutos
         ) {
}
