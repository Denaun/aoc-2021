package com.example.denaun.aoc2021.parsers;

import static org.jparsec.Scanners.isChar;

import java.util.List;
import java.util.function.BiFunction;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;

public class AocParsers {
    private AocParsers() {}

    public static final Parser<?> LINE_ENDING = isChar('\n');
    public static final Parser<?> SPACE = isChar(' ');
    public static final Parser<?> COMMA = isChar(',');

    public static final Parser<Integer> NUMBER = Scanners.INTEGER.map(Integer::valueOf);
    public static final Parser<Integer> BINARY_NUMBER =
            AocScanners.BINARY.map(s -> Integer.valueOf(s, 2));
    public static final Parser<Integer> DIGIT = AocScanners.DIGIT.map(Integer::valueOf);

    public static final Parser<List<Integer>> NUMBER_LIST = NUMBER.endBy1(LINE_ENDING);

    public static final <A, T> Parser<T> sepPair(
            Parser<?> sep, Parser<A> atom,
            BiFunction<? super A, ? super A, ? extends T> map) {
        return sepPair(atom, sep, atom, map);
    }

    public static final <A, B, T> Parser<T> sepPair(
            Parser<A> first, Parser<?> sep, Parser<B> second,
            BiFunction<? super A, ? super B, ? extends T> map) {
        return Parsers.sequence(first.followedBy(sep), second, map);
    }
}
