package com.example.denaun.aoc2021.day24;

record IntWithIndex(int value, int index) {
    static IntWithIndex of(int value, int index) {
        return new IntWithIndex(value, index);
    }

    IntWithIndex offsetBy(int offset) {
        return new IntWithIndex(value, offset + index);
    }
}
