package com.example.denaun.aoc2021.day06;

import static com.example.denaun.aoc2021.parsers.AocParsers.COMMA;
import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import org.jparsec.Parser;

class Day06 {
    private Day06() {}

    private static final Parser<List<Integer>> PARSER = NUMBER.sepBy(COMMA).followedBy(LINE_ENDING);
    private static final int REPRODUCTION_PERIOD = 6;
    private static final int FIRST_REPRODUCTION_PERIOD = REPRODUCTION_PERIOD + 2;

    static long part1(String input) {
        var ages = PARSER.parse(input);
        var ageCounts = ages.stream().collect(toCounts());
        return reproduce(ageCounts, 80);
    }

    static long part2(String input) {
        var ages = PARSER.parse(input);
        var ageCounts = ages.stream().collect(toCounts());
        return reproduce(ageCounts, 256);
    }

    static long reproduce(Map<Integer, Long> ageCounts, int days) {
        checkArgument(ageCounts.keySet().stream().allMatch(age -> age >= 0));
        for (var i = 0; i < days; ++i) {
            var next = new HashMap<Integer, Long>();
            for (var entry : ageCounts.entrySet()) {
                if (entry.getKey() == 0) {
                    add(next, REPRODUCTION_PERIOD, entry.getValue());
                    add(next, FIRST_REPRODUCTION_PERIOD, entry.getValue());
                } else {
                    add(next, entry.getKey() - 1, entry.getValue());
                }
            }
            ageCounts = next;
        }
        return ageCounts.values().stream()
                .mapToLong(i -> i)
                .sum();
    }

    private static final Collector<Object, ?, Map<Object, Long>> TO_COUNTS = Collector.of(
            HashMap<Object, Long>::new,
            (map, element) -> add(map, element, 1L),
            (map, other) -> {
                for (var entry : other.entrySet()) {
                    add(map, entry.getKey(), entry.getValue());
                }
                return map;
            },
            ImmutableMap::copyOf);

    @SuppressWarnings({"rawtypes", "unchecked"})
    static <T> Collector<T, ?, Map<T, Long>> toCounts() {
        return (Collector) TO_COUNTS;
    }

    static <T> void add(Map<T, Long> map, T key, Long value) {
        map.merge(key, value, Long::sum);
    }
}
