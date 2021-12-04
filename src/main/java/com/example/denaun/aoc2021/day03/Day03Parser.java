package com.example.denaun.aoc2021.day03;

import static com.example.denaun.aoc2021.parsers.AocParsers.BINARY_NUMBER;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;

import com.example.denaun.aoc2021.parsers.AocScanners;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day03Parser {
    private Day03Parser() {}

    static final Parser<DiagnosticReport> INPUT = Parsers.sequence(
            AocScanners.BINARY.peek().map(String::length),
            BINARY_NUMBER.endBy1(LINE_ENDING),
            DiagnosticReport::new);
}
