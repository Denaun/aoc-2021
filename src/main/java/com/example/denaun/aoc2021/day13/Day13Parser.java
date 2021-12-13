package com.example.denaun.aoc2021.day13;

import static com.example.denaun.aoc2021.parsers.AocParsers.COORDINATE;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static com.example.denaun.aoc2021.parsers.AocParsers.sepPair;
import static org.jparsec.Scanners.isChar;
import static org.jparsec.Scanners.string;

import com.example.denaun.aoc2021.Coordinate;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day13Parser {
    private Day13Parser() {}


    static final Parser<Set<Coordinate>> COORDINATES = COORDINATE.endBy1(LINE_ENDING)
            .map(ImmutableSet::copyOf);

    static final Parser<IntFunction<Fold>> FOLD_TYPE = Parsers.or(
            isChar('y').retn(Fold.Horizontal::new),
            isChar('x').retn(Fold.Vertical::new));
    static final Parser<Fold> FOLD = sepPair(
            string("fold along ").next(FOLD_TYPE),
            isChar('='),
            NUMBER,
            (type, amount) -> type.apply(amount));
    static final Parser<List<Fold>> FOLDS = FOLD.endBy1(LINE_ENDING);

    static final Parser<Manual> INPUT = sepPair(COORDINATES, LINE_ENDING, FOLDS, Manual::new);
}
