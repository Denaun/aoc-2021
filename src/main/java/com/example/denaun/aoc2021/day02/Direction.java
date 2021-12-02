package com.example.denaun.aoc2021.day02;

sealed interface Direction {
    <T extends Movable<T>> T move(T p);
}


final record Forward(int step) implements Direction {
    @Override
    public <T extends Movable<T>> T move(T p) {
        return p.forward(step);
    }
}


final record Down(int step) implements Direction {
    @Override
    public <T extends Movable<T>> T move(T p) {
        return p.down(step);
    }
}


final record Up(int step) implements Direction {
    @Override
    public <T extends Movable<T>> T move(T p) {
        return p.up(step);
    }
}
