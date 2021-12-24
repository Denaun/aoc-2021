package com.example.denaun.aoc2021.day23;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import org.jparsec.Parser;

class Day23 {
    private Day23() {}

    private static final Parser<List<Room>> PARSER = Day23Parser.INPUT;

    static int part1(String input) {
        var rooms = PARSER.parse(input);
        return minimumCost(new Burrow(rooms));
    }

    static int minimumCost(Map<? extends Location, Amphipod> start) {
        record Node(Map<Location, Amphipod> amphipods, int cost) {
        }

    static int minimumCost(Burrow start) {
        var toVisit = new PriorityQueue<WithCost<Burrow>>();
        toVisit.add(WithCost.of(start, 0));
        var visited = new HashMap<Burrow, Integer>();
        visited.put(start, 0);
        while (!toVisit.isEmpty()) {
            var current = toVisit.poll();
            if (current.value().isDone()) {
                return current.cost();
            }
            for (var move : current.value().generateMoves()) {
                var next = WithCost.of(move.value(), current.cost() + move.cost());
                if (!visited.containsKey(next.value())
                        || visited.get(next.value()) > next.cost()) {
                    visited.put(next.value(), next.cost());
                    toVisit.offer(next);
                }
            }
        }
        throw new IllegalStateException();
    }

    private static boolean isFinalState(Map<? extends Location, Amphipod> amphipods) {
        return amphipods.entrySet().stream()
                .allMatch(entry -> entry.getKey() instanceof SideRoom room
                        && room.target() == entry.getValue());
    }
}
