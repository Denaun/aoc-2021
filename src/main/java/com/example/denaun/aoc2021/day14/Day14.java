package com.example.denaun.aoc2021.day14;

import java.util.Map;
import org.jparsec.Parser;

class Day14 {
    private Day14() {}

    private static final Parser<PolymerizationInstructions> PARSER = Day14Parser.INPUT;

    static long part1(String input) {
        var instructions = PARSER.parse(input);
        var counts = instructions.estimateProcess(10);
        var mostCommon = counts.entrySet().stream()
                .mapToLong(Map.Entry::getValue)
                .max().orElseThrow();
        var leastCommon = counts.entrySet().stream()
                .mapToLong(Map.Entry::getValue)
                .min().orElseThrow();
        return mostCommon - leastCommon;
    }

    static long part2(String input) {
        var instructions = PARSER.parse(input);
        var counts = instructions.estimateProcess(40);
        var mostCommon = counts.entrySet().stream()
                .mapToLong(Map.Entry::getValue)
                .max().orElseThrow();
        var leastCommon = counts.entrySet().stream()
                .mapToLong(Map.Entry::getValue)
                .min().orElseThrow();
        return mostCommon - leastCommon;
    }
}
