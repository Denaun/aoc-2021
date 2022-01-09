package com.example.denaun.aoc2021.day25;

import com.example.denaun.aoc2021.Coordinate;

enum Facing {
    EAST {
        @Override
        Coordinate ahead(int x, int y) {
            return new Coordinate(x + 1, y);
        }

        @Override
        Coordinate behind(int x, int y) {
            return new Coordinate(x - 1, y);
        }
    },
    SOUTH {
        @Override
        Coordinate ahead(int x, int y) {
            return new Coordinate(x, y + 1);
        }

        @Override
        Coordinate behind(int x, int y) {
            return new Coordinate(x, y - 1);
        }
    };

    abstract Coordinate ahead(int x, int y);

    abstract Coordinate behind(int x, int y);
}
