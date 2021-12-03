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

    static int part2(String input) {
        var report = PARSER.parse(input);
        var oxygenGeneratorRating = report.filterBits(DiagnosticReport::mostCommonValue);
        var co2ScrubberRating = report.filterBits(DiagnosticReport::leastCommonValue);
        return oxygenGeneratorRating * co2ScrubberRating;
    }
}
