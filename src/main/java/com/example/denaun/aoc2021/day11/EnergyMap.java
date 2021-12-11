package com.example.denaun.aoc2021.day11;

import static com.google.common.base.Preconditions.checkState;

import com.example.denaun.aoc2021.Coordinate;
import com.example.denaun.aoc2021.Matrix;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

record EnergyMap(Matrix data) {
    static final int MAX_ENERGY = 9;

    static EnergyMap copyOf(EnergyMap other) {
        return new EnergyMap(Matrix.copyOf(other.data()));
    }

    int size() {
        return data.rows() * data.columns();
    }

    Set<Coordinate> step() {
        incrementAll();
        var toFlash = findOctopusesToFlash();
        var flashed = new HashSet<Coordinate>(toFlash);
        while (!toFlash.isEmpty()) {
            var current = toFlash.pop();
            checkState(data.isInside(current) && shouldFlash(current));
            data.set(current, 0);
            for (var neighbor : current.allAdjacent()) {
                if (!data.isInside(neighbor) || flashed.contains(neighbor)) {
                    continue;
                }
                increment(neighbor);
                if (shouldFlash(neighbor)) {
                    flashed.add(neighbor);
                    toFlash.add(neighbor);
                }
            }
        }
        return flashed;
    }

    private Deque<Coordinate> findOctopusesToFlash() {
        return IntStream.range(0, data.rows())
                .boxed()
                .flatMap(y -> IntStream.range(0, data.columns())
                        .filter(x -> shouldFlash(x, y))
                        .mapToObj(x -> new Coordinate(x, y)))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private void incrementAll() {
        data.replaceAll(i -> i + 1);
    }

    private void increment(Coordinate c) {
        data.set(c, data.get(c) + 1);
    }

    private boolean shouldFlash(Coordinate c) {
        return shouldFlash(c.x(), c.y());
    }

    private boolean shouldFlash(int x, int y) {
        return data.get(x, y) > MAX_ENERGY;
    }
}
