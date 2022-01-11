package com.example.denaun.aoc2021.day24;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntUnaryOperator;
import org.jparsec.Parser;

class Day24 {
    private Day24() {}

    private static final Parser<List<Stack>> PARSER = Day24Parser.INPUT;

    static long part1(String input) {
        var constraints = PARSER
                .parse(input).stream()
                .reduce(Stack::combine)
                .orElseThrow()
                .getAsConstraints();
        return crack(constraints, difference -> 9 - Integer.max(0, difference));
    }

    static long part2(String input) {
        var constraints = PARSER
                .parse(input).stream()
                .reduce(Stack::combine)
                .orElseThrow()
                .getAsConstraints();
        return crack(constraints, difference -> 1 + Integer.max(0, -difference));
    }

    static long crack(List<Constraint> constraints, IntUnaryOperator preference) {
        var size = 1 + constraints.stream()
                .mapToInt(Constraint::secondIndex)
                .max()
                .orElseThrow();
        var input = new int[size];
        for (var constraint : constraints) {
            var base = preference.applyAsInt(constraint.difference());
            input[constraint.firstIndex()] = base;
            input[constraint.secondIndex()] = base + constraint.difference();
        }
        return Arrays.stream(input)
                .mapToLong(i -> i)
                .reduce(0, (total, digit) -> total * 10 + digit);
    }
}
