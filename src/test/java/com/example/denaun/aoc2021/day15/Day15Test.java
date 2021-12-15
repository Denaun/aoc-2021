package com.example.denaun.aoc2021.day15;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import com.example.denaun.aoc2021.Coordinate;
import com.example.denaun.aoc2021.Matrix;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day15Test extends AocTestCase {
    public Day15Test() throws IOException {
        super("day15.in");
    }

    private static final RiskMap EXAMPLE_INPUT = new RiskMap(Matrix.of(
            List.of(1, 1, 6, 3, 7, 5, 1, 7, 4, 2),
            List.of(1, 3, 8, 1, 3, 7, 3, 6, 7, 2),
            List.of(2, 1, 3, 6, 5, 1, 1, 3, 2, 8),
            List.of(3, 6, 9, 4, 9, 3, 1, 5, 6, 9),
            List.of(7, 4, 6, 3, 4, 1, 7, 1, 1, 1),
            List.of(1, 3, 1, 9, 1, 2, 8, 1, 3, 7),
            List.of(1, 3, 5, 9, 9, 1, 2, 4, 2, 1),
            List.of(3, 1, 2, 5, 4, 2, 1, 6, 3, 9),
            List.of(1, 2, 9, 3, 1, 3, 8, 5, 2, 1),
            List.of(2, 3, 1, 1, 9, 4, 4, 5, 8, 1)));

    @Test
    public void example1() {
        var start = new Coordinate(0, 0);
        var end = new Coordinate(9, 9);
        assertThat(EXAMPLE_INPUT.lowestTotalRisk(start, end))
                .isEqualTo(40);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day15.part1(input)).isEqualTo(755);
    }

    @Test
    public void example2() {
        assertThat(new RiskMap(Matrix.of(List.of(8)))
                .repeatedIncreasing(5)
                .data().data())
                        .containsExactly(
                                List.of(8, 9, 1, 2, 3),
                                List.of(9, 1, 2, 3, 4),
                                List.of(1, 2, 3, 4, 5),
                                List.of(2, 3, 4, 5, 6),
                                List.of(3, 4, 5, 6, 7))
                        .inOrder();
    }

    @Test
    public void example3() {
        var map = EXAMPLE_INPUT.repeatedIncreasing(5);
        var start = new Coordinate(0, 0);
        var end = new Coordinate(map.rows() - 1, map.columns() - 1);
        assertThat(map.lowestTotalRisk(start, end))
                .isEqualTo(315);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day15.part2(input)).isEqualTo(3016);
    }
}
