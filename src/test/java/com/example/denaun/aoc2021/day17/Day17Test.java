package com.example.denaun.aoc2021.day17;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import com.example.denaun.aoc2021.Coordinate;
import java.io.IOException;
import org.junit.Test;

public class Day17Test extends AocTestCase {
    public Day17Test() throws IOException {
        super("day17.in");
    }

    private static Area EXAMPLE_INPUT = new Area(
            new Coordinate(20, -5),
            new Coordinate(30, -10));

    @Test
    public void example1() {
        assertThat(Day17.backtrackHeight(EXAMPLE_INPUT.bottom()))
                .isEqualTo(45);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day17.part1(input)).isEqualTo(5778);
    }

    @Test
    public void example2() {
        assertThat(Day17.countVelocities(EXAMPLE_INPUT))
                .isEqualTo(112);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day17.part2(input)).isEqualTo(2576);
    }
}
