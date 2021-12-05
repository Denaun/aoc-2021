package com.example.denaun.aoc2021.day02;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static com.example.denaun.aoc2021.parsers.AocParsers.SPACE;
import static com.example.denaun.aoc2021.parsers.AocParsers.sepPair;
import static org.jparsec.Scanners.string;

import java.util.List;
import java.util.function.Function;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day02Parser {
    private Day02Parser() {}

    static final Parser<Function<Integer, Direction>> KEYWORD = Parsers.or(
            string("forward").retn(Forward::new),
            string("down").retn(Down::new),
            string("up").retn(Up::new));
    static final Parser<Direction> DIRECTION = sepPair(
            KEYWORD, SPACE, NUMBER,
            (makeDirection, step) -> makeDirection.apply(step));
    static final Parser<List<Direction>> INPUT = DIRECTION.endBy1(LINE_ENDING);
}
