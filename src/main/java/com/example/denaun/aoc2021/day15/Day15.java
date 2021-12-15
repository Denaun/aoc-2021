package com.example.denaun.aoc2021.day15;

import static com.example.denaun.aoc2021.parsers.AocParsers.LEVEL_MAP;

import com.example.denaun.aoc2021.Coordinate;
import org.jparsec.Parser;

class Day15 {
    private Day15() {}

    private static final Parser<RiskMap> PARSER = LEVEL_MAP.map(RiskMap::new);

    static int part1(String input) {
        var map = PARSER.parse(input);
        var start = new Coordinate(0, 0);
        var end = new Coordinate(map.rows() - 1, map.columns() - 1);
        return map.lowestTotalRisk(start, end);
    }
}
