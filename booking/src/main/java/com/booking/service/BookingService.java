package com.booking.service;

import com.booking.data.structures.BookingTree;
import com.booking.data.structures.NodeBooking;
import com.booking.domain.Booking;
import com.booking.domain.BookingRepository;
import com.booking.error.BookingConstraint;
import com.booking.exceptions.InvalidIntervalException;
import org.springframework.stereotype.Service;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final BookingRepository repository;
    private final BookingTree bookingTree;
    private final BookingConstraint bookingConstraint;

    public BookingService(BookingRepository repository, BookingTree bookingTree, BookingConstraint bookingConstraint) {
        this.repository = repository;
        this.bookingTree = bookingTree;
        this.bookingConstraint = bookingConstraint;
    }

    public void createBooking(Booking booking){

        Errors errors = new DirectFieldBindingResult(booking, "Booking");
        this.bookingConstraint.validate(booking, errors);

        if (errors.hasErrors()){
            List<String> errorList = new ArrayList<>();
            for (ObjectError e: errors.getAllErrors()){
                errorList.add(e.getCode());
            }

            throw new InvalidIntervalException(errorList.toString());
        }

        if (!errors.hasErrors()){
            List<Booking> bookings = this.repository.findAll()
                    .stream()
                    .limit(100)
                    .collect(Collectors.toList());
            NodeBooking bookingTree = this.bookingTree.buildTree(bookings);
            this.bookingTree.search(bookingTree, booking);

            this.repository.save(booking);
        } else {
            throw new InvalidIntervalException("The interval is no valid");
        }




//        System.out.println(bookings.size());

    }


}
