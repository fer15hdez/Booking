package com.booking.controller;

import com.booking.domain.BookingConfig;
import com.booking.service.BookingConfigService;
import com.booking.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking/config")
public class BookingConfigController {

    private final BookingConfigService service;

    public BookingConfigController(BookingConfigService service) {
        this.service = service;
    }

    @PostMapping
    public BookingConfig createBookingConfig(@RequestBody BookingConfig bookingConfig){
        return this.service.createBookingConfig(bookingConfig);
    }
}
