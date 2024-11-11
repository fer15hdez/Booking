package com.booking.data.structures;

import com.booking.domain.Booking;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;

@Component
public class BookingTree {
    NodeBooking root;

    public NodeBooking buildTree(List<Booking> bookings) {
        if (bookings.isEmpty()) {
            return null;
        }

        bookings.sort((a, b) -> Long.compare(
                a.getBookingStart().toEpochSecond(ZoneOffset.UTC),
                b.getBookingStart().toEpochSecond(ZoneOffset.UTC)));
        return buildTreeUtil(bookings, 0, bookings.size() - 1);
    }

    private NodeBooking buildTreeUtil(List<Booking> intervals, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        NodeBooking node = new NodeBooking(intervals.get(mid));
        node.left = buildTreeUtil(intervals, start, mid - 1);
        node.right = buildTreeUtil(intervals, mid + 1, end);
        return node;
    }

    public void search(NodeBooking node, Booking x) {
        if (node == null) {
            return;
        }

        if (overlap(node.booking, x)) {
            System.out.println("Overlap with: " + node.booking.getBookingStart() + ", " + node.booking.getBookingEnd());
        }

        if (x.getBookingStart().toEpochSecond(ZoneOffset.UTC) < node.booking.getBookingStart().toEpochSecond(ZoneOffset.UTC)) {
            search(node.left, x);
        }

        if (x.getBookingEnd().toEpochSecond(ZoneOffset.UTC) > node.booking.getBookingStart().toEpochSecond(ZoneOffset.UTC)) {
            search(node.right, x);
        }
    }

    private boolean overlap(Booking i1, Booking i2) {
        return i1.getBookingStart().toEpochSecond(ZoneOffset.UTC) <= i2.getBookingEnd().toEpochSecond(ZoneOffset.UTC) &&
                i2.getBookingStart().toEpochSecond(ZoneOffset.UTC) <= i1.getBookingEnd().toEpochSecond(ZoneOffset.UTC);
    }
}
