package com.example.denaun.aoc2021.day05;

import static com.example.denaun.aoc2021.parsers.AocParsers.COMMA;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static org.jparsec.Scanners.string;

import java.util.List;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day05Parser {
    private Day05Parser() {}

    static final Parser<Coordinate> COORDINATE =
            Parsers.sequence(
                    NUMBER.followedBy(COMMA),
                    NUMBER,
                    Coordinate::new);
    static final Parser<Line> LINE =
            Parsers.sequence(
                    COORDINATE.followedBy(string(" -> ")),
                    COORDINATE,
                    Line::new);
    static final Parser<List<Line>> INPUT = LINE.endBy1(LINE_ENDING);
}
