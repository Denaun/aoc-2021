package com.example.denaun.aoc2021.day20;

enum Cell {
    UNLIT(0) {
        @Override
        public String toString() {
            return ".";
        }
    },
    LIT(1) {
        @Override
        public String toString() {
            return "#";
        }
    };

    private final int value;

    Cell(int value) {
        this.value = value;
    }

    int value() {
        return value;
    }
}
