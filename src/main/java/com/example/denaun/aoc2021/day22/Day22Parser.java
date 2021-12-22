package com.example.denaun.aoc2021.day22;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.SIGNED;
import static com.example.denaun.aoc2021.parsers.AocParsers.SPACE;
import static com.example.denaun.aoc2021.parsers.AocParsers.sepPair;
import static org.jparsec.Parsers.or;
import static org.jparsec.Parsers.sequence;
import static org.jparsec.Scanners.string;

import com.google.common.collect.Range;
import java.util.List;
import org.jparsec.Parser;

class Day22Parser {
    private Day22Parser() {}

    private static final Parser<Range<Integer>> RANGE =
            sepPair(string(".."), SIGNED, Range::closed);
    static final Parser<Cuboid<Integer>> CUBOID = sequence(
            string("x=").next(RANGE),
            string(",y=").next(RANGE),
            string(",z=").next(RANGE),
            Cuboid::new);
    private static final Parser<State> STATE = or(
            string("on").retn(State.ON),
            string("off").retn(State.OFF));
    private static final Parser<RebootStep> REBOOT_STEP =
            sepPair(STATE, SPACE, CUBOID, RebootStep::new);

    static final Parser<List<RebootStep>> INPUT = REBOOT_STEP.endBy1(LINE_ENDING);
}
