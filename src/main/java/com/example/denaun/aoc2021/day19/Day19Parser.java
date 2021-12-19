package com.example.denaun.aoc2021.day19;

import static com.example.denaun.aoc2021.parsers.AocParsers.COMMA;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static com.example.denaun.aoc2021.parsers.AocParsers.SIGNED;
import static org.jparsec.Scanners.string;

import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Set;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day19Parser {
    private Day19Parser() {}

    private static final Parser<Coordinate3d> BEACON = Parsers.sequence(
            SIGNED.followedBy(COMMA),
            SIGNED.followedBy(COMMA),
            SIGNED,
            Coordinate3d::new);

    private static final Parser<Set<Coordinate3d>> BEACONS =
            BEACON.endBy1(LINE_ENDING).map(ImmutableSet::copyOf);

    static final Parser<List<Set<Coordinate3d>>> INPUT =
            NUMBER.between(string("--- scanner "), string(" ---\n"))
                    .next(BEACONS)
                    .sepBy1(LINE_ENDING);
}
