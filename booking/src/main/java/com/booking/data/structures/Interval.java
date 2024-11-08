package com.booking.data.structures;

public class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public String toString(){
        return "[" + this.start + ", " + this.end + "]";
    }
}
