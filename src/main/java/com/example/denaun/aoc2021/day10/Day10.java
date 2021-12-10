package com.example.denaun.aoc2021.day10;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.jparsec.Parser;

class Day10 {
    private Day10() {}

    private static final Parser<List<List<Token>>> PARSER = Day10Parser.INPUT;
    private static final Map<ChunkType, Integer> SCORES = Map.of(
            ChunkType.ROUND, 3,
            ChunkType.SQUARE, 57,
            ChunkType.CURLY, 1197,
            ChunkType.ANGLE, 25137);

    static int part1(String input) {
        var code = PARSER.parse(input);
        return code.stream()
                .map(Day10::checkCorruption)
                .flatMap(Optional::stream)
                .mapToInt(SCORES::get)
                .sum();
    }

    static Optional<ChunkType> checkCorruption(List<Token> line) {
        var nesting = new ArrayDeque<ChunkType>();
        for (var token : line) {
            if (token.function() == TokenFunction.OPEN) {
                nesting.push(token.type());
            } else {
                assert token.function() == TokenFunction.CLOSE;
                var expected = nesting.pop();
                if (expected != token.type()) {
                    return Optional.of(token.type());
                }
            }
        }
        return Optional.empty();
    }
}
