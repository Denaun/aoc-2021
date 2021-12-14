package com.example.denaun.aoc2021.day14;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import org.jparsec.Parser;

class Day14 {
    private Day14() {}

    private static final Parser<PolymerizationInstructions> PARSER = Day14Parser.INPUT;

    static int part1(String input) {
        var instructions = PARSER.parse(input);
        var polymer = instructions.process(10);
        var counts = ImmutableMultiset.copyOf(polymer);
        var mostCommon = counts.entrySet().stream()
                .mapToInt(Multiset.Entry::getCount)
                .max().orElseThrow();
        var leastCommon = counts.entrySet().stream()
                .mapToInt(Multiset.Entry::getCount)
                .min().orElseThrow();
        return mostCommon - leastCommon;
    }
}
