package com.example.denaun.aoc2021.day15;

import static com.google.common.base.Verify.verify;

import com.example.denaun.aoc2021.Coordinate;
import com.example.denaun.aoc2021.Matrix;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

record RiskMap(Matrix<Integer> data) {
    static final int MIN_RISK = 1;
    static final int MAX_RISK = 9;

    int rows() {
        return data.rows();
    }

    int columns() {
        return data.columns();
    }

    RiskMap repeatedIncreasing(int times) {
        return new RiskMap(new Matrix<>(
                IntStream.range(0, times)
                        .boxed()
                        .flatMap(i -> data.data().stream()
                                .map(row -> IntStream.range(0, times)
                                        .boxed()
                                        .flatMap(j -> {
                                            var off = i + j;
                                            return row.stream()
                                                    .map(v -> MIN_RISK
                                                            + (v - MIN_RISK + off) % MAX_RISK);
                                        })
                                        .toList()))
                        .toList()));

    }

    int lowestTotalRisk(Coordinate start, Coordinate end) {
        record Node(Coordinate coordinate, int risk) {
            int estimateTotalRisk(Coordinate end) {
                return risk + l1Distance(coordinate, end);
            }
        }

        var toVisit = new PriorityQueue<Node>(
                Comparator.comparingInt(n -> n.estimateTotalRisk(end)));
        toVisit.offer(new Node(start, 0));
        var visited = new HashMap<Coordinate, Integer>();
        visited.put(start, 0);
        while (!toVisit.isEmpty()) {
            var current = toVisit.poll();
            if (end.equals(current.coordinate)) {
                verify(current.estimateTotalRisk(end) == current.risk);
                return current.risk;
            }
            current.coordinate
                    .adjacent().stream()
                    .filter(data::isInside)
                    .map(neighbor -> new Node(neighbor, current.risk + data.get(neighbor)))
                    .filter(node -> !visited.containsKey(node.coordinate)
                            || visited.get(node.coordinate) > node.risk)
                    .forEach(node -> {
                        visited.put(node.coordinate, node.risk);
                        toVisit.offer(node);
                    });
        }
        throw new IllegalStateException("No path found");
    }

    private static int l1Distance(Coordinate a, Coordinate b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
