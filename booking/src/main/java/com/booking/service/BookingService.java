package com.booking.service;

import com.booking.data.structures.BookingTree;
import com.booking.data.structures.NodeBooking;
import com.booking.domain.Booking;
import com.booking.domain.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final BookingRepository repository;
    private final BookingTree bookingTree;

    public BookingService(BookingRepository repository, BookingTree bookingTree) {
        this.repository = repository;
        this.bookingTree = bookingTree;
    }

    public void createBooking(Booking booking){
        List<Booking> bookings = this.repository.findAll()
                .stream()
                .limit(100)
                .collect(Collectors.toList());
        NodeBooking bookingTree = this.bookingTree.buildTree(bookings);
        this.bookingTree.search(bookingTree, booking);

        this.repository.save(booking);

        System.out.println(bookings.size());

    }


}
