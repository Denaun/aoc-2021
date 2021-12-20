package com.example.denaun.aoc2021.day09;

import com.example.denaun.aoc2021.Coordinate;
import com.example.denaun.aoc2021.Matrix;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

record HeightMap(Matrix<Integer> data) {
    static final int MAX_HEIGHT = 9;

    int rows() {
        return data.rows();
    }

    int columns() {
        return data.columns();
    }

    int get(int x, int y) {
        if (!data.isInside(x, y)) {
            return Integer.MAX_VALUE;
        }
        return data.get(x, y);
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

    Set<Coordinate> expandBasin(Coordinate c) {
        var visited = new HashSet<Coordinate>();
        var toVisit = new LinkedList<Coordinate>();
        visited.add(c);
        toVisit.add(c);
        while (!toVisit.isEmpty()) {
            var current = toVisit.pop();
            var threshold = get(current);
            for (var neighbor : current.adjacent()) {
                var height = get(neighbor);
                if (height <= threshold || height >= MAX_HEIGHT
                        || visited.contains(neighbor)) {
                    continue;
                }
                visited.add(neighbor);
                toVisit.add(neighbor);
            }
        }
        return visited;
    }
}
