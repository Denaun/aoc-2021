package com.example.denaun.aoc2021.day17;

import com.example.denaun.aoc2021.Coordinate;

record Area(Coordinate topLeft, Coordinate bottomRight) {
    int top() {
        return topLeft.y();
    }

    int bottom() {
        return bottomRight.y();
    }

    int left() {
        return topLeft.x();
    }

    int right() {
        return bottomRight.x();
    }

    boolean isInside(int x, int y) {
        return x >= left()
                && x <= right()
                && y >= bottom()
                && y <= top();
    }
}
