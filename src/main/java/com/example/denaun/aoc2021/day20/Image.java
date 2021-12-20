package com.example.denaun.aoc2021.day20;

import static com.google.common.base.Preconditions.checkArgument;

import com.example.denaun.aoc2021.Matrix;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

record Image(Matrix<Cell> data, Cell rest) {
    Image(Matrix<Cell> data) {
        this(data, Cell.UNLIT);
    }

    int rows() {
        return data.rows();
    }

    int columns() {
        return data.columns();
    }

    long countLit() {
        return data.stream().filter(Cell.LIT::equals).count();
    }

    Cell get(int x, int y) {
        if (!data.isInside(x, y)) {
            return rest;
        }
        return data.get(x, y);
    }

    int windowAt(int x, int y) {
        return IntStream.rangeClosed(-1, 1)
                .flatMap(dy -> IntStream.rangeClosed(-1, 1)
                        .map(dx -> get(x + dx, y + dy).value() << windowElementIndex(dx, dy)))
                .reduce(0, (value, bit) -> value | bit);
    }

    private int windowElementIndex(int dx, int dy) {
        checkArgument(dx >= -1 && dx <= 1);
        checkArgument(dy >= -1 && dy <= 1);
        return (1 - dx) + (1 - dy) * 3;
    }

    Image enhance(ImageEnhancementAlgorithm algorithm) {
        var data = new Matrix<>(
                IntStream.range(-1, rows() + 1)
                        .mapToObj(y -> IntStream.range(-1, columns() + 1)
                                .mapToObj(x -> algorithm.get(windowAt(x, y)))
                                .toList())
                        .toList());
        var rest = switch (this.rest) {
            case UNLIT -> algorithm.get(0);
            case LIT -> algorithm.get((1 << 9) - 1);
        };
        return new Image(data, rest);
    }

    @Override
    public String toString() {
        return data.data().stream()
                .map(row -> row.stream()
                        .map(Cell::toString)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));
    }
}
