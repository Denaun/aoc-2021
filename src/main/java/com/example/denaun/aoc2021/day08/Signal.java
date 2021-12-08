package com.example.denaun.aoc2021.day08;

enum Signal {
    A, B, C, D, E, F, G;

    static Signal valueOf(char c) {
        return switch (c) {
            case 'a' -> Signal.A;
            case 'b' -> Signal.B;
            case 'c' -> Signal.C;
            case 'd' -> Signal.D;
            case 'e' -> Signal.E;
            case 'f' -> Signal.F;
            case 'g' -> Signal.G;
            default -> throw new IllegalStateException(
                    "Unexpected char: %c".formatted(c));
        };
    }
}
