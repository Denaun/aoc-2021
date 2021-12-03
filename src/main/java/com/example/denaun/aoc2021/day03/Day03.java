package com.example.denaun.aoc2021.day03;

import org.jparsec.Parser;

class Day03 {
    private Day03() {}

    private static final Parser<DiagnosticReport> PARSER = Day03Parser.INPUT;

    static int part1(String input) {
        var report = PARSER.parse(input);
        var mostCommon = report.mostCommonBits();
        var gammaRate = mostCommon;
        var epsilonRate = ~mostCommon & ((1 << report.numBits()) - 1);
        return gammaRate * epsilonRate;
    }
}
