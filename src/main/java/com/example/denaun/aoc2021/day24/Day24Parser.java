package com.example.denaun.aoc2021.day24;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static com.example.denaun.aoc2021.parsers.AocParsers.SIGNED;
import static com.example.denaun.aoc2021.parsers.AocParsers.SPACE;
import static org.jparsec.Parsers.sequence;
import static org.jparsec.Scanners.string;

import java.util.List;
import org.jparsec.Parser;

class Day24Parser {
    private Day24Parser() {}

    private static final Parser<Stack> CHUNK =
            sequence(
                    string("""
                            inp w
                            mul x 0
                            add x z
                            mod x 26
                            div z""")
                            .next(NUMBER.between(SPACE, LINE_ENDING)),
                    string("add x")
                            .next(SIGNED.between(SPACE, LINE_ENDING)),
                    string("""
                            eql x w
                            eql x 0
                            mul y 0
                            add y 25
                            mul y x
                            add y 1
                            mul z y
                            mul y 0
                            add y w
                            add y""")
                            .next(SIGNED.between(SPACE, LINE_ENDING))
                            .followedBy(string("""
                                    mul y x
                                    add z y""")),
                    (opcode, correction, offset) -> switch (opcode) {
                    case 1 -> Stack.push(offset);
                    case 26 -> Stack.pop(correction);
                    default -> throw new IllegalArgumentException();
                    });

    static final Parser<List<Stack>> INPUT = CHUNK.endBy1(LINE_ENDING);
}
