package com.example.denaun.aoc2021.day17;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.SIGNED;
import static org.jparsec.Scanners.string;

import com.example.denaun.aoc2021.Coordinate;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day17Parser {
    private Day17Parser() {}

    static final Parser<Area> AREA = Parsers.sequence(
            string("target area: x=").next(SIGNED),
            string("..").next(SIGNED),
            string(", y=").next(SIGNED),
            string("..").next(SIGNED),
            (xMin, xMax, yMin, yMax) -> new Area(
                    new Coordinate(xMin, yMax),
                    new Coordinate(xMax, yMin)))
            .followedBy(LINE_ENDING);
}
