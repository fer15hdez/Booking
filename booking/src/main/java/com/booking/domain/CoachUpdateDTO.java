package com.booking.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachUpdateDTO {
    @NotNull
    private Integer id;
    @Size(min = 3, max = 20)
    private String name;
    @Size(max = 100)
    private String description;
    private Integer availability;
    private List<Booking> bookings;
    List<Area> areas;
}
