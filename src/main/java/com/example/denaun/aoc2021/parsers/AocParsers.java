package com.example.denaun.aoc2021.parsers;

import static org.jparsec.Scanners.isChar;

import java.util.List;
import org.jparsec.Parser;
import org.jparsec.Scanners;

public class AocParsers {
    private AocParsers() {}

    public static final Parser<?> LINE_ENDING = isChar('\n');
    public static final Parser<?> SPACE = isChar(' ');

    public static final Parser<Integer> NUMBER = Scanners.INTEGER.map(Integer::valueOf);
    public static final Parser<Integer> BINARY_NUMBER =
            AocScanners.BINARY.map(s -> Integer.valueOf(s, 2));

    public static final Parser<List<Integer>> NUMBER_LIST = NUMBER.endBy1(LINE_ENDING);
}
