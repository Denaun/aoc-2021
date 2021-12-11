package com.example.denaun.aoc2021.day11;

import static com.example.denaun.aoc2021.parsers.AocParsers.LEVEL_MAP;

import java.util.stream.IntStream;
import org.jparsec.Parser;

class Day11 {
    private Day11() {}


    private static final Parser<EnergyMap> PARSER = LEVEL_MAP.map(EnergyMap::new);

    static int part1(String input) {
        var map = PARSER.parse(input);
        return IntStream.range(0, 100)
                .map(i -> map.step().size())
                .sum();
    }

    static int part2(String input) {
        var map = PARSER.parse(input);
        return IntStream.iterate(1, i -> i + 1)
                .filter(i -> map.step().size() == map.size())
                .findFirst()
                .orElseThrow();
    }
}
