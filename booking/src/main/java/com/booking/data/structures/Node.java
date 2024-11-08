package com.booking.data.structures;

public class Node {
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
