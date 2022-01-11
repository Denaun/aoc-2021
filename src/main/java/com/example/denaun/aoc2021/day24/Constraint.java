package com.example.denaun.aoc2021.day24;

record Constraint(
        int difference,
        int firstIndex,
        int secondIndex) {
    Constraint offsetBy(int offset) {
        return new Constraint(difference, offset + firstIndex, offset + secondIndex);
    }
}
