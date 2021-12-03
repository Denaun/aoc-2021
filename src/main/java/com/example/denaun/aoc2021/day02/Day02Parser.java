package com.example.denaun.aoc2021.day02;

import static org.jparsec.Scanners.isChar;
import static org.jparsec.Scanners.string;

import com.example.denaun.aoc2021.parsers.AocParsers;
import java.util.List;
import java.util.function.Function;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day02Parser {
    private Day02Parser() {}

    static final Parser<Function<Integer, Direction>> DIRECTIONS = Parsers.or(
            string("forward").retn(Forward::new),
            string("down").retn(Down::new),
            string("up").retn(Up::new));
    static final Parser<Direction> DIRECTION = Parsers.sequence(
            DIRECTIONS.followedBy(isChar(' ')),
            AocParsers.NUMBER,
            (makeDirection, step) -> makeDirection.apply(step));
    static final Parser<List<Direction>> INPUT = AocParsers.listOf(DIRECTION);
}
