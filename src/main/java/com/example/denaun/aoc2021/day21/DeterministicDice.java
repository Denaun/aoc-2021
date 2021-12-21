package com.example.denaun.aoc2021.day21;

import java.util.function.IntSupplier;

class DeterministicDice implements IntSupplier {
    private static final int MAX = 1000;
    private int value = 0;

    @Override
    public int getAsInt() {
        value += 1;
        if (value > MAX) {
            value = 0;
        }
        return value;
    }
}
