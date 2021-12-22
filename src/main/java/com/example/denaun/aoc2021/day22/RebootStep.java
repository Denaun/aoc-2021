package com.example.denaun.aoc2021.day22;

record RebootStep(State state, Cuboid<Integer> cuboid) {
    void executeOn(CuboidSet<Integer> cuboids) {
        state.accept(cuboids, cuboid);
    }
}
