package com.example.denaun.aoc2021.day06;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day06Test extends AocTestCase {
    public Day06Test() throws IOException {
        super("day06.in");
    }

    private static final List<Integer> EXAMPLE_INPUT = List.of(3, 4, 3, 1, 2);

    @Test
    public void example1() {
        var ageCounts = EXAMPLE_INPUT.stream().collect(Day06.toCounts());
        assertThat(Day06.reproduce(ageCounts, 18)).isEqualTo(26);
        assertThat(Day06.reproduce(ageCounts, 80)).isEqualTo(5934);
        assertThat(Day06.reproduce(ageCounts, 256)).isEqualTo(26_984_457_539L);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day06.part1(input)).isEqualTo(385391);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day06.part2(input)).isEqualTo(1_728_611_055_389L);
    }
}
