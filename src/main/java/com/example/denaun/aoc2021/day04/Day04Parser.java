package com.example.denaun.aoc2021.day04;

import static com.example.denaun.aoc2021.parsers.AocParsers.COMMA;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static com.example.denaun.aoc2021.parsers.AocParsers.SPACE;

import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day04Parser {
    private Day04Parser() {}

    static final Parser<?> SEP = LINE_ENDING.times(2);
    static final Parser<Board> BOARD =
            SPACE.optional(null).next(NUMBER)
                    .sepBy1(Parsers.or(SPACE, LINE_ENDING))
                    .map(Board::new);
    static final Parser<Bingo> INPUT = Parsers.sequence(
            NUMBER.sepBy1(COMMA).followedBy(SEP),
            BOARD.sepBy1(SEP).followedBy(LINE_ENDING),
            Bingo::new);
}
