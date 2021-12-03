package com.example.denaun.aoc2021.day03;

import com.example.denaun.aoc2021.parsers.AocParsers;
import com.example.denaun.aoc2021.parsers.AocScanners;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day03Parser {
    private Day03Parser() {}

    static final Parser<DiagnosticReport> INPUT = Parsers.sequence(
            AocScanners.BINARY.peek().map(String::length),
            AocParsers.listOf(AocParsers.BINARY_NUMBER),
            DiagnosticReport::new);
}
