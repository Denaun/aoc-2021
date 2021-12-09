package com.example.denaun.aoc2021.day05;

import static com.example.denaun.aoc2021.parsers.AocParsers.COMMA;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static com.example.denaun.aoc2021.parsers.AocParsers.sepPair;
import static org.jparsec.Scanners.string;

import com.example.denaun.aoc2021.Coordinate;
import java.util.List;
import org.jparsec.Parser;

class Day05Parser {
    private Day05Parser() {}

    static final Parser<Coordinate> COORDINATE = sepPair(COMMA, NUMBER, Coordinate::new);
    static final Parser<Line> LINE = sepPair(string(" -> "), COORDINATE, Line::new);
    static final Parser<List<Line>> INPUT = LINE.endBy1(LINE_ENDING);
}
