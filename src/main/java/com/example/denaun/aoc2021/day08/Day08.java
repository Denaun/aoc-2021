package com.example.denaun.aoc2021.day08;

import java.util.List;
import java.util.Set;
import org.jparsec.Parser;

class Day08 {
    private Day08() {}

    private static final Parser<List<DisplayObservation>> PARSER = Day08Parser.INPUT;

    static long part1(String input) {
        var observations = PARSER.parse(input);
        return observations.stream()
                .flatMap(i -> i.output().stream())
                .filter(Day08::isRecognizablePattern)
                .count();
    }

    static boolean isRecognizablePattern(Set<Signal> signals) {
        return switch (signals.size()) {
            // Representations of 1, 4, 7, 8 respectively.
            case 2, 4, 3, 7 -> true;
            default -> false;
        };
    }
}
