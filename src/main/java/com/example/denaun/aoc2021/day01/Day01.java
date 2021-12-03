package com.example.denaun.aoc2021.day01;

import com.example.denaun.aoc2021.parsers.AocParsers;
import com.google.common.collect.Streams;
import java.util.List;
import java.util.stream.IntStream;
import org.jparsec.Parser;

class Day01 {
    private Day01() {}

    private static final Parser<List<Integer>> PARSER = AocParsers.NUMBER_LIST;
    private static final int WINDOW_SIZE = 3;

    static long part1(String input) {
        var heights = PARSER.parse(input);
        return countIncrements(heights);
    }

    static long part2(String input) {
        var heights = PARSER.parse(input);
        var slidingHeights = slidingSums(heights, WINDOW_SIZE);
        return countIncrements(slidingHeights);
    }

    static long countIncrements(List<Integer> values) {
        return Streams
                .zip(values.stream(), values.stream().skip(1),
                        (previous, current) -> current > previous)
                .filter(Boolean::booleanValue)
                .count();
    }

    static List<Integer> slidingSums(List<Integer> values, int size) {
        return IntStream.range(0, values.size() - size + 1)
                .mapToObj(i -> values.stream()
                        .skip(i).limit(size)
                        .reduce(Integer::sum)
                        .orElseThrow())
                .toList();
    }
}
