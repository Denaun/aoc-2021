package com.example.denaun.aoc2021;

import java.util.List;

public final record Coordinate(int x, int y) {
    public Coordinate left() {
        return new Coordinate(x - 1, y);
    }

    public Coordinate right() {
        return new Coordinate(x + 1, y);
    }

    public Coordinate up() {
        return new Coordinate(x, y - 1);
    }

    public Coordinate down() {
        return new Coordinate(x, y + 1);
    }

    public List<Coordinate> adjacent() {
        return List.of(left(), right(), up(), down());
    }

    public List<Coordinate> allAdjacent() {
        return List.of(
                left(),
                left().up(),
                left().down(),
                right(),
                right().up(),
                right().down(),
                up(),
                down());
    }
}
