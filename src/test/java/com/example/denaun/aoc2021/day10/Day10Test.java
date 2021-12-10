package com.example.denaun.aoc2021.day10;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
        var corruptions = EXAMPLE_INPUT.stream()
                .map(Day10::checkCorruption)
                .toList();
        assertThat(corruptions)
                .containsExactly(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of(ChunkType.CURLY),
                        Optional.empty(),
                        Optional.of(ChunkType.ROUND),
                        Optional.of(ChunkType.SQUARE),
                        Optional.empty(),
                        Optional.of(ChunkType.ROUND),
                        Optional.of(ChunkType.ANGLE),
                        Optional.empty())
                .inOrder();;
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day10.part1(input)).isEqualTo(366_027);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
