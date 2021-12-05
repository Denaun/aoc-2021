package com.example.denaun.aoc2021.day05;

import static com.google.common.base.Preconditions.checkState;

import java.util.stream.IntStream;
import java.util.stream.Stream;

record Line(Coordinate p1, Coordinate p2) {
    static Line of(int x1, int y1, int x2, int y2) {
        return new Line(new Coordinate(x1, y1), new Coordinate(x2, y2));
    }

    boolean isStraight() {
        return p1.x() == p2.x() || p1.y() == p2.y();
    }

    Stream<Coordinate> points() {
        checkState(isStraight());
        if (p1.x() == p2.x()) {
            var start = Integer.min(p1.y(), p2.y());
            var end = Integer.max(p1.y(), p2.y());
            return IntStream.rangeClosed(start, end)
                    .mapToObj(y -> new Coordinate(p1.x(), y));
        }
        var start = Integer.min(p1.x(), p2.x());
        var end = Integer.max(p1.x(), p2.x());
        return IntStream.rangeClosed(start, end)
                .mapToObj(x -> new Coordinate(x, p1.y()));
    }
}
