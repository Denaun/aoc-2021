package com.example.denaun.aoc2021.day18;

import static com.example.denaun.aoc2021.parsers.AocParsers.COMMA;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static org.jparsec.Scanners.isChar;

import com.example.denaun.aoc2021.parsers.AocParsers;
import java.util.List;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day18Parser {
    private Day18Parser() {}

    private static final Parser<Regular> REGULAR = NUMBER.map(Regular::of);

    private static Parser<Pair> pair(Parser<Number> number) {
        return AocParsers.sepPair(COMMA, number, Pair::new)
                .between(isChar('['), isChar(']'));
    }

    static final Parser<Number> number() {
        var ref = Parser.<Number>newReference();
        var parser = Parsers.<Number>or(REGULAR, pair(ref.lazy()));
        ref.set(parser);
        return parser;
    }

    static final Parser<List<Number>> INPUT = number().endBy1(LINE_ENDING);
}
