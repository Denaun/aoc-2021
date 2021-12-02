package com.example.denaun.aoc2021.day02;

record Trajectory(Position position, int aim) implements Movable<Trajectory> {

    private static final Trajectory CENTER = new Trajectory(Position.center(), 0);

    static Trajectory center() {
        return CENTER;
    }

    Trajectory(int horizontal, int depth, int aim) {
        this(new Position(horizontal, depth), aim);
    }

    int horizontal() {
        return position.horizontal();
    }

    int depth() {
        return position.depth();
    }

    @Override
    public Trajectory forward(int step) {
        return new Trajectory(position.forward(step).down(aim * step), aim);
    }

    @Override
    public Trajectory down(int step) {
        return new Trajectory(position, aim + step);
    }

    @Override
    public Trajectory up(int step) {
        return new Trajectory(position, aim - step);
    }
}
