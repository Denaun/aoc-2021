package com.example.denaun.aoc2021.day04;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Collections;

record Board(List<Integer> numbers) {
    static final int EDGE_SIZE = 5;
    static final int SIZE = EDGE_SIZE * EDGE_SIZE;

    public Board {
        checkArgument(numbers.size() == SIZE);
        numbers = Collections.unmodifiableList(numbers);
    }

    static Board of(Integer... args) {
        return new Board(List.of(args));
    }
}
