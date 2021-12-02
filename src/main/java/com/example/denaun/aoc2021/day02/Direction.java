package com.example.denaun.aoc2021.day02;

sealed interface Direction {
    Position move(Position p);
}


final record Forward(int step) implements Direction {
    @Override
    public Position move(Position p) {
        return p.forward(step);
    }
}


final record Down(int step) implements Direction {
    @Override
    public Position move(Position p) {
        return p.down(step);
    }
}


final record Up(int step) implements Direction {
    @Override
    public Position move(Position p) {
        return p.up(step);
    }
}
