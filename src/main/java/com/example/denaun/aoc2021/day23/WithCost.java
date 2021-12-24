package com.example.denaun.aoc2021.day23;

record WithCost<T> (T value, int cost) implements Comparable<WithCost<T>> {
    static <T> WithCost<T> of(T value, int cost) {
        return new WithCost<>(value, cost);
    }

    @Override
    public int compareTo(WithCost<T> o) {
        return Integer.compare(cost, o.cost);
    }
}
