package com.example.denaun.aoc2021.day21;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

class MultipleDie implements IntSupplier {
    private final IntSupplier backingDice;
    private final int times;

    MultipleDie(IntSupplier backingDice, int times) {
        this.backingDice = backingDice;
        this.times = times;
    }

    @Override
    public int getAsInt() {
        return IntStream.generate(backingDice::getAsInt).limit(times).sum();
    }
}
