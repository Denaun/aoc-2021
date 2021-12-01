package com.example.denaun.aoc2021.day01;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day01Test extends AocTestCase {
    public Day01Test() throws IOException {
        super("day01.in");
    }

    private static final List<Integer> EXAMPLE_INPUT = List.of(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263);

    @Test
    public void example1() {
        assertThat(Day01.countIncrements(EXAMPLE_INPUT))
                .isEqualTo(7);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day01.part1(input)).isEqualTo(1342);
    }

    @Test
    public void example2() {
        var slidingSums = Day01.slidingSums(EXAMPLE_INPUT, 3);
        assertThat(slidingSums)
                .containsExactly(
                        607,
                        618,
                        618,
                        617,
                        647,
                        716,
                        769,
                        792);
        assertThat(Day01.countIncrements(slidingSums))
                .isEqualTo(5);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day01.part2(input)).isEqualTo(1378);
    }
}
