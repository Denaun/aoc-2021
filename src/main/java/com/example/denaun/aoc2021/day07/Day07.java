package com.example.denaun.aoc2021.day07;

import static com.example.denaun.aoc2021.parsers.AocParsers.COMMA;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;

import com.google.common.collect.HashMultiset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.jparsec.Parser;

class Day07 {
    private Day07() {}

    private static final Parser<List<Integer>> PARSER = NUMBER.sepBy(COMMA).followedBy(LINE_ENDING);

    static int part1(String input) {
        var positions = PARSER.parse(input);
        var destination = nearestPosition(positions);
        return destination.getValue();
    }

    static final Map.Entry<Integer, Integer> nearestPosition(List<Integer> positions) {
        var counts = HashMultiset.create(positions);
        return counts.stream()
                .map(destination -> Map.entry(
                        destination,
                        counts.entrySet().stream()
                                .mapToInt(entry -> entry.getCount()
                                        * Math.abs(entry.getElement() - destination))
                                .sum()))
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .orElseThrow();
    }
}
