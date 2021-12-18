package com.example.denaun.aoc2021.day18;

import java.util.Collection;
import java.util.Comparator;
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

    static int part2(String input) {
        var numbers = PARSER.parse(input);
        var result = largestSum(numbers);
        return result.magnitude();
    }

    static Number sumAll(Collection<Number> numbers) {
        return numbers.stream()
                .reduce((left, right) -> Number.sum(left, right).fullyReduced())
                .orElseThrow();
    }

    static Number largestSum(Collection<Number> numbers) {
        return numbers.stream()
                .flatMap(x -> numbers.stream()
                        .filter(y -> y != x)
                        .map(y -> Number.sum(x, y).fullyReduced()))
                .max(Comparator.comparingInt(Number::magnitude))
                .orElseThrow();
    }
}
