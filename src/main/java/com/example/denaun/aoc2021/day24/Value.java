package com.example.denaun.aoc2021.day24;

import java.util.Map;
import java.util.function.ToIntFunction;

sealed interface Value extends ToIntFunction<Map<Variable, Integer>> {
}


enum Variable implements Value {
    W, X, Y, Z;

    @Override
    public int applyAsInt(Map<Variable, Integer> variables) {
        return variables.get(this);
    }
}


record Number(int value) implements Value {
    @Override
    public int applyAsInt(Map<Variable, Integer> variables) {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
