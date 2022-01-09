package com.example.denaun.aoc2021.day25;

import org.jparsec.Parser;

class Day25 {
    private Day25() {}

    private static final Parser<HerdMap> PARSER = Day25Parser.INPUT;

    static int part1(String input) {
        var map = PARSER.parse(input);
        return stepsToEverythingStuck(map);
    }

    static int stepsToEverythingStuck(HerdMap map) {
        for (var steps = 1;; ++steps) {
            var prev = map;
            map = map.step();
            if (prev.equals(map)) {
                return steps;
            }
        }
    }
}
