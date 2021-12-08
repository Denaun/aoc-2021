package com.example.denaun.aoc2021.day07;

import static com.example.denaun.aoc2021.parsers.AocParsers.COMMA;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;

import com.google.common.collect.HashMultiset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import org.jparsec.Parser;

class Day07 {
    private Day07() {}

    private static final Parser<List<Integer>> PARSER = NUMBER.sepBy(COMMA).followedBy(LINE_ENDING);
    static final IntFunction<Integer> SIMPLE_COST = i -> i;
    static final IntFunction<Integer> INCREASING_COST = i -> (i * (i + 1)) / 2;

    static int part1(String input) {
        var positions = PARSER.parse(input);
        var destination = nearestPosition(positions, SIMPLE_COST);
        return destination.getValue();
    }

    static int part2(String input) {
        var positions = PARSER.parse(input);
        var destination = nearestPosition(positions, INCREASING_COST);
        return destination.getValue();
    }

    static final Map.Entry<Integer, Integer> nearestPosition(
            List<Integer> positions,
            IntFunction<Integer> cost) {
        var counts = HashMultiset.create(positions);
        var start = Collections.min(positions);
        var end = Collections.max(positions);
        return IntStream.range(start, end)
                .mapToObj(destination -> Map.entry(
                        destination,
                        counts.entrySet().stream()
                                .mapToInt(entry -> entry.getCount()
                                        * cost.apply(Math.abs(entry.getElement() - destination)))
                                .sum()))
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .orElseThrow();
    }
}
