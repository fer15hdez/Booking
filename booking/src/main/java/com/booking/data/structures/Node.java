package com.booking.data.structures;

import com.booking.domain.Booking;

import java.awt.print.Book;
public class Node {
//    Interval interval;
    Interval interval;
    Node left;
    Node right;

    public Node(Interval interval) {
        this.interval = interval;
    }


    public String toString(){
        return "Interval: " + interval + " left: " + left.interval + " right: " + right.interval;
    }

}
