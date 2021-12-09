package com.example.denaun.aoc2021.day09;

import static com.google.common.base.Preconditions.checkArgument;

import com.example.denaun.aoc2021.Coordinate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

record HeightMap(List<List<Integer>> data) {
    static final int MAX_HEIGHT = 9;

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
