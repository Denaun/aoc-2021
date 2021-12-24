package com.example.denaun.aoc2021.day23;

enum Amphipod {
    AMBER(1), BRONZE(10), COPPER(100), DESERT(1000);

    private final int cost;

    Amphipod(int cost) {
        this.cost = cost;
    }

    int cost() {
        return cost;
    }
}
