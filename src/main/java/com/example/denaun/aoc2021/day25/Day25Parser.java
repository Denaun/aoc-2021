package com.example.denaun.aoc2021.day25;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static org.jparsec.Parsers.or;
import static org.jparsec.Scanners.isChar;

import com.example.denaun.aoc2021.Matrix;
import java.util.Optional;
import org.jparsec.Parser;

class Day25Parser {
    private Day25Parser() {}

    private static final Parser<Optional<Facing>> CELL = or(
            isChar('.').retn(Optional.empty()),
            isChar('>').retn(Optional.of(Facing.EAST)),
            isChar('v').retn(Optional.of(Facing.SOUTH)));

    static final Parser<HerdMap> INPUT =
            CELL.many1().endBy1(LINE_ENDING)
                    .map(Matrix<Optional<Facing>>::new)
                    .map(HerdMap::new);
}
