package com.example.denaun.aoc2021.day09;

import static com.example.denaun.aoc2021.parsers.AocParsers.DIGIT;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;

import com.example.denaun.aoc2021.Coordinate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.jparsec.Parser;

class Day09 {
    private Day09() {}

    private static final Parser<HeightMap> PARSER =
            DIGIT.many1().endBy1(LINE_ENDING).map(HeightMap::new);

    static int part1(String input) {
        var map = PARSER.parse(input);
        return lowPoints(map)
                .mapToInt(map::get)
                .map(Day09::riskLevel)
                .sum();
    }

    static Stream<Coordinate> lowPoints(HeightMap map) {
        return IntStream.range(0, map.rows())
                .boxed()
                .flatMap(y -> IntStream.range(0, map.columns())
                        .mapToObj(x -> new Coordinate(x, y))
                        .filter(map::isLowPoint));
    }

    static int riskLevel(int height) {
        return height + 1;
    }
}
