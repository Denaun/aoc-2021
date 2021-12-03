package com.example.denaun.aoc2021.parsers;

import org.jparsec.Parser;
import org.jparsec.pattern.Patterns;

public class AocScanners {
    private AocScanners() {}

    public static final Parser<String> BINARY = Patterns.many1(AocPredicates.IS_BINARY)
            .toScanner("binary").source();
}
