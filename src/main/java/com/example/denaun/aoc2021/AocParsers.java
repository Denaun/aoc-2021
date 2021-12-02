package com.example.denaun.aoc2021;

import static org.jparsec.Scanners.isChar;

import java.util.List;
import org.jparsec.Parser;
import org.jparsec.Scanners;

public class AocParsers {
    private AocParsers() {}

    public static final Parser<?> LINE_ENDING = isChar('\n');

    public static final Parser<Integer> NUMBER = Scanners.INTEGER.map(Integer::valueOf);

    public static final Parser<List<Integer>> NUMBER_LIST = listOf(NUMBER);

    public static final <T> Parser<List<T>> listOf(Parser<T> atom) {
        return listOf(atom, LINE_ENDING);
    }

    public static final <T> Parser<List<T>> listOf(Parser<T> atom, Parser<?> sep) {
        return atom.followedBy(sep).many1();
    }
}
