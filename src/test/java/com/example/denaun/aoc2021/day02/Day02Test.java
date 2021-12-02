package com.example.denaun.aoc2021.day02;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day02Test extends AocTestCase {
    public Day02Test() throws IOException {
        super("day02.in");
    }

    private static final List<Direction> EXAMPLE_INPUT = List.of(
            new Forward(5),
            new Down(5),
            new Forward(8),
            new Up(3),
            new Down(8),
            new Forward(2));

    @Test
    public void example1() {
        assertThat(Day02.applyDirections(Position.center(), EXAMPLE_INPUT))
                .isEqualTo(new Position(15, 10));
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day02.part1(input)).isEqualTo(2117664);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day02.part2(input)).isEqualTo(2073416724);
    }

    @Test
    public void example2() {
        assertThat(Day02.applyDirections(Trajectory.center(), EXAMPLE_INPUT)
                .position())
                        .isEqualTo(new Position(15, 60));
    }
}
