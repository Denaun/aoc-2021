package com.example.denaun.aoc2021.day25;

import com.example.denaun.aoc2021.Coordinate;
import com.example.denaun.aoc2021.Matrix;
import java.util.Optional;
import java.util.stream.IntStream;

record HerdMap(Matrix<Optional<Facing>> data) {
    HerdMap step() {
        return move(Facing.EAST).move(Facing.SOUTH);
    }

    HerdMap move(Facing facing) {
        var occupied = Optional.of(facing);
        return new HerdMap(new Matrix<Optional<Facing>>(IntStream
                .range(0, data.rows())
                .mapToObj(y -> IntStream
                        .range(0, data.columns())
                        .mapToObj(x -> {
                            if (data.get(x, y).isEmpty()
                                    && get(facing.behind(x, y)).equals(occupied)) {
                                return occupied;
                            }
                            if (data.get(x, y).equals(occupied)
                                    && get(facing.ahead(x, y)).isEmpty()) {
                                return Optional.<Facing>empty();
                            }
                            return data.get(x, y);
                        })
                        .toList())
                .toList()));
    }

    private Optional<Facing> get(Coordinate c) {
        return data.get(
                (c.x() + data.columns()) % data.columns(),
                (c.y() + data.rows()) % data.rows());
    }
}
