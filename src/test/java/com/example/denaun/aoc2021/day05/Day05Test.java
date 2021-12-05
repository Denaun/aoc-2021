package com.example.denaun.aoc2021.day05;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day05Test extends AocTestCase {
    public Day05Test() throws IOException {
        super("day05.in");
    }

    private static final List<Line> EXAMPLE_INPUT = List.of(
            Line.of(0, 9, 5, 9),
            Line.of(8, 0, 0, 8),
            Line.of(9, 4, 3, 4),
            Line.of(2, 2, 2, 1),
            Line.of(7, 0, 7, 4),
            Line.of(6, 4, 2, 0),
            Line.of(0, 9, 2, 9),
            Line.of(3, 4, 1, 4),
            Line.of(0, 0, 8, 8),
            Line.of(5, 5, 8, 2));

    @Test
    public void example1() {
        var lines = EXAMPLE_INPUT.stream()
                .filter(line -> line.isStraight())
                .toList();
        assertThat(Day05.dangerousAreas(lines)).isEqualTo(5);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day05.part1(input)).isEqualTo(6005);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
