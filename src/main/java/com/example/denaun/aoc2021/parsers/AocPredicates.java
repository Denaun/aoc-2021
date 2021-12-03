package com.example.denaun.aoc2021.parsers;

import org.jparsec.pattern.CharPredicate;
import org.jparsec.pattern.CharPredicates;

public class AocPredicates {
    private AocPredicates() {}

    public static final CharPredicate IS_BINARY = CharPredicates.range('0', '1');
}
