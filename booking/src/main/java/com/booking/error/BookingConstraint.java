package com.booking.error;

import com.booking.domain.Booking;
import com.booking.domain.BookingConfig;
import com.booking.domain.BookingConfigRepository;
import com.booking.exceptions.BookingConfigEntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

// 1- El intervalo debe estar entre los límites definidos en la clase BookingConfig.
// 2- La fecha inicial tiene que ser > ahora. _ok_
// 3- La fecha inicial tiene que ser < fecha final. _ok_
// 4- El rango tiene que ser > minTimeReservation. _ok_
// 5-
@Component
public class BookingConstraint implements Validator {

    private final BookingConfigRepository repository;

    public BookingConstraint(BookingConfigRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Booking.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Booking booking = (Booking) target;
        LocalDateTime bookingStart=  booking.getBookingStart();
        LocalDateTime bookingEnd = booking.getBookingEnd();

        Optional<BookingConfig> bookingConfig = repository.findById(1);

        interval(bookingStart, bookingEnd, errors);
        if (bookingConfig.isPresent()){
            if (this.isUpperMinTimeReservation(bookingStart, bookingEnd)){
                errors.reject( "The minimum reservation time must exceed " + bookingConfig.get().getMinTimeReservation());
            }
        }else {
            throw new BookingConfigEntityNotFoundException("There are not defined a configuration entity");
        }


    }

    public Boolean isValid(LocalDateTime start, LocalDateTime end){
        if (!this.isUpperMinTimeReservation(start, end)){
            System.out.println("this.isUpperMinTimeReservation(start, end) false");
            return  false;
        }
        return true;
    }

    public void interval(LocalDateTime start, LocalDateTime end, Errors error) {
        Optional<BookingConfig> bookingConfig = repository.findById(1);

        if (bookingConfig.isPresent()) {
            LocalDateTime dateStartConfig = bookingConfig.get().getLowerLimitInterval();
            LocalDateTime dateEndConfig = bookingConfig.get().getUpperLimitInterval();

            if (!start.isAfter(dateStartConfig)) {
                error.rejectValue("bookingStart", "The start date must be after " + dateStartConfig.toString());
            }
            if (!start.isBefore(dateEndConfig)) {
                error.rejectValue("dateStartConfig", "The start date must be before " + dateStartConfig.toString());
            }
            if (!start.isBefore(end)) {
                error.rejectValue("bookingStart", "The start date must be earlier than the end date");
            }
            if (!start.isAfter(LocalDateTime.now())) {
                error.rejectValue("bookingStart", "The start date must be later than now");
            }
            if (!end.isBefore(dateEndConfig)) {
                error.rejectValue("bookingEnd", "The end date must be before " + dateEndConfig.toString());
            }
        }
    }

    public Boolean isUpperMinTimeReservation(LocalDateTime start, LocalDateTime end){
        Optional<BookingConfig> bookingConfig = repository.findById(1);

        return bookingConfig.filter(config -> this.convertIntervalToMin(start, end) > config.getMinTimeReservation()).isPresent();
    }

        public Long convertIntervalToMin (LocalDateTime start, LocalDateTime end){
            Instant startInstant = start.toInstant(ZoneOffset.UTC);
            Instant endInstant = end.toInstant(ZoneOffset.UTC);

        return Duration.between(startInstant, endInstant).toMinutes();
        }


    }

