package com.example.denaun.aoc2021.day01;

import com.example.denaun.aoc2021.AocParsers;
import com.google.common.collect.Streams;
import java.util.List;
import org.jparsec.Parser;

class Day01 {
    private Day01() {}

    private static final Parser<List<Integer>> PARSER = AocParsers.NUMBER_LIST;

    static long part1(String input) {
        var heights = PARSER.parse(input);
        return countIncrements(heights);
    }

    static long countIncrements(List<Integer> values) {
        return Streams
                .zip(values.stream(), values.stream().skip(1),
                        (previous, current) -> current > previous)
                .filter(Boolean::booleanValue)
                .count();
    }
}
