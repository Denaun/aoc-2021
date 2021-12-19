package com.example.denaun.aoc2021.day19;

record Coordinate3d(int x, int y, int z) {

    private static final Coordinate3d ORIGIN = new Coordinate3d(0, 0, 0);
    static Coordinate3d origin() {
        return ORIGIN;
    }

    Coordinate3d sum(Coordinate3d other) {
        return new Coordinate3d(x + other.x, y + other.y, z + other.z);
    }

    Coordinate3d difference(Coordinate3d other) {
        return new Coordinate3d(x - other.x, y - other.y, z - other.z);
    }

    int distance(Coordinate3d other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
    }
}
