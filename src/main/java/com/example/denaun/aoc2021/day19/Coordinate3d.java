package com.example.denaun.aoc2021.day19;

record Coordinate3d(int x, int y, int z) {
    Coordinate3d sum(Coordinate3d other) {
        return new Coordinate3d(x + other.x, y + other.y, z + other.z);
    }

    Coordinate3d difference(Coordinate3d other) {
        return new Coordinate3d(x - other.x, y - other.y, z - other.z);
    }
}
