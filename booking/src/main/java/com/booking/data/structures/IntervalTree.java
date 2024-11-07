package com.booking.data.structures;

import java.util.List;

public class IntervalTree {
    Node root;

    public Node buildTree(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return null;
        }

        intervals.sort((a, b) -> Integer.compare(a.start, b.start));
        return buildTreeUtil(intervals, 0, intervals.size() - 1);
    }

    private Node buildTreeUtil(List<Interval> intervals, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(intervals.get(mid));
        node.left = buildTreeUtil(intervals, start, mid - 1);
        node.right = buildTreeUtil(intervals, mid + 1, end);
        return node;
    }

    public void search(Node node, Interval x) {
        if (node == null) {
            return;
        }

        if (overlap(node.interval, x)) {
            System.out.println("Overlap with: " + node.interval.start + ", " + node.interval.end);
        }

        if (x.start < node.interval.start) {
            search(node.left, x);
        }

        if (x.end > node.interval.start) {
            search(node.right, x);
        }
    }

    private boolean overlap(Interval i1, Interval i2) {
        return i1.start <= i2.end && i2.start <= i1.end;
    }
}
