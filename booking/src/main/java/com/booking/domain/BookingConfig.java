package com.booking.domain;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class BookingConfig {
    private LocalDateTime minDateTime;
    private LocalDateTime maxDateTime;
    private Integer minTimeReservation;
    private Integer maxTimeReservation;

    public boolean isValid(
            LocalDateTime minDateTime,
            LocalDateTime maxDateTime,
            Integer minTimeReservation,
            Integer maxTimeReservation
            ){
        return minDateTime.isAfter(this.minDateTime) &&
                maxDateTime.isBefore(this.maxDateTime) &&
                minTimeReservation >= this.minTimeReservation &&
                maxTimeReservation <= this.maxTimeReservation;

    }
}
