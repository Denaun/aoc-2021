package com.example.denaun.aoc2021.day02;

record Position(int horizontal, int depth) {
    Position forward(int step) {
        return new Position(horizontal + step, depth);
    }

    Position down(int step) {
        return new Position(horizontal, depth + step);
    }

    Position up(int step) {
        return new Position(horizontal, depth - step);
    }
}
