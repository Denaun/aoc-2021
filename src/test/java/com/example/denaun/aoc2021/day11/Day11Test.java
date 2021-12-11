package com.example.denaun.aoc2021.day11;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import com.example.denaun.aoc2021.Coordinate;
import com.example.denaun.aoc2021.Matrix;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;

public class Day11Test extends AocTestCase {
    public Day11Test() throws IOException {
        super("day11.in");
    }

    private static final Matrix EXAMPLE_INPUT = Matrix.of(
            List.of(5, 4, 8, 3, 1, 4, 3, 2, 2, 3),
            List.of(2, 7, 4, 5, 8, 5, 4, 7, 1, 1),
            List.of(5, 2, 6, 4, 5, 5, 6, 1, 7, 3),
            List.of(6, 1, 4, 1, 3, 3, 6, 1, 4, 6),
            List.of(6, 3, 5, 7, 3, 8, 5, 4, 7, 8),
            List.of(4, 1, 6, 7, 5, 2, 4, 6, 4, 5),
            List.of(2, 1, 7, 6, 8, 4, 1, 7, 2, 1),
            List.of(6, 8, 8, 2, 8, 8, 1, 1, 3, 4),
            List.of(4, 8, 4, 6, 8, 4, 8, 5, 5, 4),
            List.of(5, 2, 8, 3, 7, 5, 1, 5, 2, 6));

    @Test
    public void example1() {
        var map = new EnergyMap(Matrix.of(
                List.of(1, 1, 1, 1, 1),
                List.of(1, 9, 9, 9, 1),
                List.of(1, 9, 1, 9, 1),
                List.of(1, 9, 9, 9, 1),
                List.of(1, 1, 1, 1, 1)));
        assertThat(map.step()).containsExactly(
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(3, 1),
                new Coordinate(1, 2),
                new Coordinate(2, 2),
                new Coordinate(3, 2),
                new Coordinate(1, 3),
                new Coordinate(2, 3),
                new Coordinate(3, 3));
        assertThat(map.step()).isEmpty();
    }

    @Test
    public void example2() {
        var map = new EnergyMap(Matrix.copyOf(EXAMPLE_INPUT));
        assertThat(IntStream.range(0, 10)
                .map(i -> map.step().size())
                .sum())
                        .isEqualTo(204);
        assertThat(IntStream.range(0, 90)
                .map(i -> map.step().size())
                .sum())
                        .isEqualTo(1656 - 204);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day11.part1(input)).isEqualTo(1694);
    }

    @Test
    public void example3() {
        var map = new EnergyMap(Matrix.copyOf(EXAMPLE_INPUT));
        assertThat(IntStream.iterate(1, i -> i + 1)
                .filter(i -> map.step().size() == map.size())
                .findFirst()
                .orElseThrow())
                        .isEqualTo(195);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day11.part2(input)).isEqualTo(346);
    }
}
