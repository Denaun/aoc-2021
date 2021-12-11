package com.example.denaun.aoc2021.day09;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import com.example.denaun.aoc2021.Matrix;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.junit.Test;

public class Day09Test extends AocTestCase {
    public Day09Test() throws IOException {
        super("day09.in");
    }

    private static final HeightMap EXAMPLE_INPUT = new HeightMap(Matrix.of(
            List.of(2, 1, 9, 9, 9, 4, 3, 2, 1, 0),
            List.of(3, 9, 8, 7, 8, 9, 4, 9, 2, 1),
            List.of(9, 8, 5, 6, 7, 8, 9, 8, 9, 2),
            List.of(8, 7, 6, 7, 8, 9, 6, 7, 8, 9),
            List.of(9, 8, 9, 9, 9, 6, 5, 6, 7, 8)));

    @Test
    public void example1() {
        assertThat(Day09.lowPoints(EXAMPLE_INPUT)
                .mapToInt(EXAMPLE_INPUT::get)
                .toArray())
                        .asList().containsExactly(1, 0, 5, 5);
        assertThat(Day09.lowPoints(EXAMPLE_INPUT)
                .mapToInt(EXAMPLE_INPUT::get)
                .map(Day09::riskLevel)
                .sum())
                        .isEqualTo(15);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day09.part1(input)).isEqualTo(545);
    }

    @Test
    public void example2() {
        assertThat(Day09.lowPoints(EXAMPLE_INPUT)
                .map(EXAMPLE_INPUT::expandBasin)
                .mapToInt(Collection::size)
                .toArray())
                        .asList().containsExactly(3, 9, 14, 9);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day09.part2(input)).isEqualTo(950_600);
    }
}
