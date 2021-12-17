package com.example.denaun.aoc2021.day17;

import org.jparsec.Parser;

class Day17 {
    private Day17() {}

    private static final Parser<Area> PARSER = Day17Parser.AREA;

    static int part1(String input) {
        var area = PARSER.parse(input);
        return backtrackHeight(area.bottomRight().y());
    }

    static int backtrackHeight(int depth) {
        // We need to pass through 0, and have a velocity of maximum `|depth|`
        // at the next step so that we don't get out. Decrease by one to get the
        // previous step.
        var initialVelocity = -depth - 1;
        return (initialVelocity * (initialVelocity + 1)) / 2;
    }
}
