package com.example.denaun.aoc2021.day13;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toUnmodifiableSet;

import com.example.denaun.aoc2021.Coordinate;
import java.util.Set;

sealed interface Fold {
    Set<Coordinate> apply(Set<Coordinate> dots);

    static record Horizontal(int amount) implements Fold {
        @Override
        public Set<Coordinate> apply(Set<Coordinate> dots) {
            checkArgument(dots.stream().noneMatch(dot -> dot.y() == amount));
            return dots.stream()
                    .map(dot -> {
                        if (dot.y() < amount) {
                            return dot;
                        } else {
                            return new Coordinate(dot.x(), 2 * amount - dot.y());
                        }
                    })
                    .collect(toUnmodifiableSet());
        }
    }
    static record Vertical(int amount) implements Fold {
        @Override
        public Set<Coordinate> apply(Set<Coordinate> dots) {
            checkArgument(dots.stream().noneMatch(dot -> dot.x() == amount));
            return dots.stream()
                    .map(dot -> {
                        if (dot.x() < amount) {
                            return dot;
                        } else {
                            return new Coordinate(2 * amount - dot.x(), dot.y());
                        }
                    })
                    .collect(toUnmodifiableSet());
        }
    }
}

