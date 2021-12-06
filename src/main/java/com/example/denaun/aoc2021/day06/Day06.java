package com.example.denaun.aoc2021.day06;

import static com.example.denaun.aoc2021.parsers.AocParsers.COMMA;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import java.util.List;
import org.jparsec.Parser;

class Day06 {
    private Day06() {}

    private static final Parser<List<Integer>> PARSER = NUMBER.sepBy(COMMA).followedBy(LINE_ENDING);
    private static final int REPRODUCTION_PERIOD = 6;
    private static final int FIRST_REPRODUCTION_PERIOD = REPRODUCTION_PERIOD + 2;

    static long part1(String input) {
        var ages = PARSER.parse(input);
        var ageCounts = ImmutableMultiset.copyOf(ages);
        return reproduce(ageCounts, 80);
    }

    static long reproduce(Multiset<Integer> ageCounts, int days) {
        checkArgument(ageCounts.stream().allMatch(age -> age >= 0));
        for (var i = 0; i < days; ++i) {
            var next = HashMultiset.<Integer>create();
            for (var entry : ageCounts.entrySet()) {
                if (entry.getElement() == 0) {
                    next.add(REPRODUCTION_PERIOD, entry.getCount());
                    next.add(FIRST_REPRODUCTION_PERIOD, entry.getCount());
                } else {
                    next.add(entry.getElement() - 1, entry.getCount());
                }
            }
            ageCounts = next;
        }
        return ageCounts.size();
    }
}
