package com.example.denaun.aoc2021.day13;

import org.jparsec.Parser;

class Day13 {
    private Day13() {}

    private static final Parser<Manual> PARSER = Day13Parser.INPUT;

    static int part1(String input) {
        var manual = PARSER.parse(input);
        return manual.instructions()
                .get(0)
                .apply(manual.dots())
                .size();
    }
}
