package com.example.denaun.aoc2021.day23;

record WithIndex<T> (T value, int index) {
    static <T> WithIndex<T> of(T value, int index) {
        return new WithIndex<>(value, index);
    }
}
