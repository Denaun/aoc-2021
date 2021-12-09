package com.example.denaun.aoc2021.day09;

import static com.google.common.base.Preconditions.checkArgument;

import com.example.denaun.aoc2021.Coordinate;
import java.util.List;

record HeightMap(List<List<Integer>> data) {
    HeightMap {
        checkArgument(!data.isEmpty());
        var numColumns = data.get(0).size();
        checkArgument(data.stream().skip(1)
                .allMatch(column -> column.size() == numColumns));
    }

    int rows() {
        return data.size();
    }

    int columns() {
        return data.get(0).size();
    }

    int get(int x, int y) {
        if (y < 0) {
            return Integer.MAX_VALUE;
        }
        if (y >= rows()) {
            return Integer.MAX_VALUE;
        }
        if (x < 0) {
            return Integer.MAX_VALUE;
        }
        if (x >= columns()) {
            return Integer.MAX_VALUE;
        }
        return data.get(y).get(x);
    }

    int get(Coordinate c) {
        return get(c.x(), c.y());
    }

    boolean isLowPoint(int x, int y) {
        var v = get(x, y);
        return get(x - 1, y) > v
                && get(x + 1, y) > v
                && get(x, y - 1) > v
                && get(x, y + 1) > v;
    }

    boolean isLowPoint(Coordinate c) {
        return isLowPoint(c.x(), c.y());
    }
}
