package com.example.denaun.aoc2021.day08;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.SPACE;
import static com.example.denaun.aoc2021.parsers.AocParsers.sepPair;
import static java.util.stream.Collectors.toCollection;
import static org.jparsec.Scanners.string;

import java.util.EnumSet;
import java.util.List;
import org.jparsec.Parser;
import org.jparsec.Scanners;
import org.jparsec.pattern.CharPredicates;

class Day08Parser {
    private Day08Parser() {}

    static final Parser<EnumSet<Signal>> SIGNALS =
            Scanners.many1(CharPredicates.among("abcdefg")).source()
                    .map(s -> s.chars()
                            .mapToObj(c -> Signal.valueOf((char) c))
                            .collect(toCollection(() -> EnumSet.noneOf(Signal.class))));
    static final Parser<DisplayObservation> DISPLAY_OBSERVATION = sepPair(
            SIGNALS.sepBy(SPACE),
            string(" | "),
            SIGNALS.sepBy(SPACE),
            DisplayObservation::new);
    static final Parser<List<DisplayObservation>> INPUT = DISPLAY_OBSERVATION.endBy1(LINE_ENDING);
}
