package com.example.denaun.aoc2021.day10;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day10Test extends AocTestCase {
    public Day10Test() throws IOException {
        super("day10.in");
    }

    private static final List<List<Token>> EXAMPLE_INPUT = Day10Parser.INPUT.parse("""
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
            """);

    @Test
    public void example1() {
        var errors = EXAMPLE_INPUT.stream()
                .map(Day10::findError)
                .toList();
        assertThat(errors)
                .containsExactly(
                        new Incomplete(List.of(
                                ChunkType.CURLY, ChunkType.CURLY, ChunkType.SQUARE,
                                ChunkType.SQUARE, ChunkType.ROUND, ChunkType.CURLY, ChunkType.ROUND,
                                ChunkType.SQUARE)),
                        new Incomplete(List.of(
                                ChunkType.ROUND, ChunkType.CURLY, ChunkType.ANGLE, ChunkType.SQUARE,
                                ChunkType.CURLY, ChunkType.ROUND)),
                        new Corrupted(ChunkType.CURLY),
                        new Incomplete(List.of(
                                ChunkType.CURLY, ChunkType.CURLY, ChunkType.ANGLE, ChunkType.CURLY,
                                ChunkType.ANGLE, ChunkType.ROUND, ChunkType.ROUND,
                                ChunkType.ROUND, ChunkType.ROUND)),
                        new Corrupted(ChunkType.ROUND),
                        new Corrupted(ChunkType.SQUARE),
                        new Incomplete(List.of(
                                ChunkType.SQUARE, ChunkType.SQUARE, ChunkType.CURLY,
                                ChunkType.CURLY, ChunkType.SQUARE, ChunkType.CURLY,
                                ChunkType.SQUARE, ChunkType.CURLY, ChunkType.ANGLE)),
                        new Corrupted(ChunkType.ROUND),
                        new Corrupted(ChunkType.ANGLE),
                        new Incomplete(List.of(
                                ChunkType.SQUARE, ChunkType.ROUND, ChunkType.CURLY,
                                ChunkType.ANGLE)))
                .inOrder();
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day10.part1(input)).isEqualTo(366_027);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day10.part2(input)).isEqualTo(1_118_645_287);
    }
}
