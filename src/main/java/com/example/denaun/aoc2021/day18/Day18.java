package com.example.denaun.aoc2021.day18;

import java.util.Collection;
import java.util.List;
import org.jparsec.Parser;

class Day18 {
    private Day18() {}

    private static final Parser<List<Number>> PARSER = Day18Parser.INPUT;

    static int part1(String input) {
        var numbers = PARSER.parse(input);
        var result = sumAll(numbers);
        return result.magnitude();
    }

    static Number sumAll(Collection<Number> numbers) {
        return numbers.stream()
                .reduce((left, right) -> Number.sum(left, right).fullyReduced())
                .orElseThrow();
    }
}
