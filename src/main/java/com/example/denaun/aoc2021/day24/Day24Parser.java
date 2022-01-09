package com.example.denaun.aoc2021.day24;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.SIGNED;
import static com.example.denaun.aoc2021.parsers.AocParsers.SPACE;
import static com.example.denaun.aoc2021.parsers.AocParsers.sepPair;
import static org.jparsec.Parsers.or;
import static org.jparsec.Scanners.isChar;
import static org.jparsec.Scanners.string;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import org.jparsec.Parser;

class Day24Parser {
    private Day24Parser() {}


    private static final Parser<Variable> VARIABLE = or(
            isChar('w').retn(Variable.W),
            isChar('x').retn(Variable.X),
            isChar('y').retn(Variable.Y),
            isChar('z').retn(Variable.Z));
    private static final Parser<Value> VALUE = or(VARIABLE, SIGNED.map(Number::new));

    private static Parser<Inp> inpGenerator() {
        final var indices = IntStream.iterate(0, i -> i + 1).iterator();
        return VARIABLE.map(variable -> new Inp(variable, indices.nextInt()));
    }

    private static <T extends FixedInstruction> Parser<T> fixedInstruction(
            BiFunction<Variable, Integer, T> factory) {
        return sepPair(VARIABLE, SPACE, SIGNED, factory);
    }

    private static <T extends BinaryInstruction> Parser<T> binaryInstruction(
            BiFunction<Variable, Value, T> factory) {
        return sepPair(VARIABLE, SPACE, VALUE, factory);
    }

    private static Parser<Instruction> instruction() {
        return or(
                string("inp ").next(inpGenerator()),
                string("add ").next(binaryInstruction(Add::new)),
                string("mul ").next(binaryInstruction(Mul::new)),
                string("div ").next(fixedInstruction(Div::new)),
                string("mod ").next(fixedInstruction(Mod::new)),
                string("eql ").next(binaryInstruction(Eql::new)));
    }

    static Parser<List<Instruction>> input() {
        return instruction().endBy1(LINE_ENDING);
    }
}
