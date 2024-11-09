package com.booking.data.structures;

import com.booking.domain.Booking;

public class NodeBooking {
//    Interval interval;
    Booking booking;
    NodeBooking left;
    NodeBooking right;

   /* public Node(Interval interval) {
        this.interval = interval;
    }*/
    public NodeBooking(Booking booking){
        this.booking = booking;
    }

  /*  public String toString(){
        return "Interval: " + interval + " left: " + left.interval + " right: " + right.interval;
    }*/
  public String toString(){
      return "Booking: " + this.booking + " booking start: " + this.left.booking + " booking right: " + this.right.booking;
  }
}
