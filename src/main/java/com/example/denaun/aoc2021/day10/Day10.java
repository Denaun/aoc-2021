package com.example.denaun.aoc2021.day10;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Verify.verify;

import com.google.common.collect.ImmutableList;
import com.google.common.math.Quantiles;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import org.jparsec.Parser;

class Day10 {
    private Day10() {}

    private static final Parser<List<List<Token>>> PARSER = Day10Parser.INPUT;
    private static final Map<ChunkType, Integer> SYNTAX_SCORES = Map.of(
            ChunkType.ROUND, 3,
            ChunkType.SQUARE, 57,
            ChunkType.CURLY, 1197,
            ChunkType.ANGLE, 25137);
    private static final Map<ChunkType, Integer> AUTOCOMPLETE_POINTS = Map.of(
            ChunkType.ROUND, 1,
            ChunkType.SQUARE, 2,
            ChunkType.CURLY, 3,
            ChunkType.ANGLE, 4);

    static int part1(String input) {
        var code = PARSER.parse(input);
        return code.stream()
                .map(Day10::findError)
                .filter(Corrupted.class::isInstance)
                .map(Corrupted.class::cast)
                .map(Corrupted::type)
                .mapToInt(SYNTAX_SCORES::get)
                .sum();
    }

    static long part2(String input) {
        var code = PARSER.parse(input);

        return (long) Quantiles.median()
                .compute(code.stream()
                        .map(Day10::findError)
                        .filter(Incomplete.class::isInstance)
                        .map(Incomplete.class::cast)
                        .map(Incomplete::missing)
                        .mapToLong(Day10::autocompleteScore)
                        .toArray());
    }

    static Error findError(List<Token> line) {
        var nesting = new ArrayDeque<ChunkType>();
        for (var token : line) {
            if (token.function() == TokenFunction.OPEN) {
                nesting.push(token.type());
            } else {
                verify(token.function() == TokenFunction.CLOSE);
                var expected = nesting.pop();
                if (expected != token.type()) {
                    return new Corrupted(token.type());
                }
            }
        }
        checkArgument(!nesting.isEmpty(), "No error found");
        return new Incomplete(ImmutableList.copyOf(nesting));
    }

    private static long autocompleteScore(List<ChunkType> missing) {
        return missing.stream()
                .mapToLong(AUTOCOMPLETE_POINTS::get)
                .reduce(0L, (totalScore, points) -> 5 * totalScore + points);
    }
}
