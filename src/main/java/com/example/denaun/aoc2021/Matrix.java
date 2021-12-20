package com.example.denaun.aoc2021;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public final record Matrix<T> (List<List<T>> data) {
    public Matrix {
        checkArgument(!data.isEmpty());
        var numColumns = data.get(0).size();
        checkArgument(data.stream().skip(1)
                .allMatch(column -> column.size() == numColumns));
        data = data.stream()
                .map(row -> (List<T>) new ArrayList<>(row))
                .toList();
    }

    @SafeVarargs
    public static <T> Matrix<T> of(List<T>... rows) {
        return new Matrix<>(List.of(rows));
    }

    public static <T> Matrix<T> copyOf(Matrix<T> other) {
        return new Matrix<>(other.data());
    }

    public int rows() {
        return data.size();
    }

    public int columns() {
        return data.get(0).size();
    }

    public boolean isInside(Coordinate c) {
        return isInside(c.x(), c.y());
    }

    public boolean isInside(int x, int y) {
        return y >= 0
                && y < rows()
                && x >= 0
                && x < columns();
    }

    public T get(Coordinate c) {
        return get(c.x(), c.y());
    }

    public T get(int x, int y) {
        return data.get(y).get(x);
    }

    public T set(Coordinate c, T v) {
        return set(c.x(), c.y(), v);
    }

    public T set(int x, int y, T v) {
        return data.get(y).set(x, v);
    }

    public void replaceAll(UnaryOperator<T> operator) {
        for (var row : data) {
            row.replaceAll(operator);
        }
    }
}
