package com.example.denaun.aoc2021.day02;

record Position(int horizontal, int depth) implements Movable<Position> {
    private static final Position CENTER = new Position(0, 0);

    static Position center() {
        return CENTER;
    }

    @Override
    public Position forward(int step) {
        return new Position(horizontal + step, depth);
    }

    @Override
    public Position down(int step) {
        return new Position(horizontal, depth + step);
    }

    @Override
    public Position up(int step) {
        return new Position(horizontal, depth - step);
    }
}
