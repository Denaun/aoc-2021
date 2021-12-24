package com.example.denaun.aoc2021.day23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import org.jparsec.Parser;

class Day23 {
    private Day23() {}

    private static final Parser<List<Room>> PARSER = Day23Parser.INPUT;

    static int part1(String input) {
        var rooms = PARSER.parse(input);
        return minimumCost(new Burrow(rooms));
    }

    static int part2(String input) {
        var rooms = PARSER.parse(input);
        return minimumCost(new Burrow(unfold(rooms)));
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

    static List<Room> unfold(List<Room> rooms) {
        return rooms.stream()
                .map(room -> {
                    var slots = new ArrayList<>(room.slots());
                    slots.addAll(1, switch (room.id()) {
                        case AMBER -> List.of(
                                Optional.of(Amphipod.DESERT),
                                Optional.of(Amphipod.DESERT));
                        case BRONZE -> List.of(
                                Optional.of(Amphipod.COPPER),
                                Optional.of(Amphipod.BRONZE));
                        case COPPER -> List.of(
                                Optional.of(Amphipod.BRONZE),
                                Optional.of(Amphipod.AMBER));
                        case DESERT -> List.of(
                                Optional.of(Amphipod.AMBER),
                                Optional.of(Amphipod.COPPER));
                    });
                    return new Room(room.id(), slots);
                })
                .toList();
    }
}
