package com.example.denaun.aoc2021.day22;

enum State {
    ON {
        @Override
        <C extends Comparable<C>> void accept(CuboidSet<C> cuboids, Cuboid<C> cuboid) {
            cuboids.add(cuboid);
        }
    },
    OFF {
        @Override
        <C extends Comparable<C>> void accept(CuboidSet<C> cuboids, Cuboid<C> cuboid) {
            cuboids.remove(cuboid);
        }
    };

    abstract <C extends Comparable<C>> void accept(CuboidSet<C> cuboids, Cuboid<C> cuboid);
}
