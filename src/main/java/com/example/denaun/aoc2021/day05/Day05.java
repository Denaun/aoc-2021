package com.example.denaun.aoc2021.day05;

import com.google.common.collect.HashMultiset;
import java.util.List;
import java.util.stream.Collectors;
import org.jparsec.Parser;

class Day05 {
    private Day05() {}

    private static final Parser<List<Line>> PARSER = Day05Parser.INPUT;

    static long part1(String input) {
        var lines = PARSER.parse(input);
        lines.removeIf(line -> !line.isStraight());
        return dangerousAreas(lines);
    }

    static long dangerousAreas(List<Line> lines) {
        return lines.stream()
                .flatMap(Line::points)
                .collect(Collectors.toCollection(HashMultiset::create))
                .entrySet().stream()
                .filter(entry -> entry.getCount() > 1)
                .count();
    }
}
