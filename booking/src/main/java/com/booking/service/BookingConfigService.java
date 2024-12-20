package com.booking.service;

import com.booking.domain.BookingConfig;
import com.booking.domain.BookingConfigRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingConfigService {

    private final BookingConfigRepository repository;

    public BookingConfigService(BookingConfigRepository repository) {
        this.repository = repository;
    }

    public BookingConfig createBookingConfig(BookingConfig bookingConfig){
        return this.repository.save(bookingConfig);
    }
}
