package com.example.denaun.aoc2021.day06;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import com.google.common.collect.ImmutableMultiset;
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
        var ageCounts = ImmutableMultiset.copyOf(EXAMPLE_INPUT);
        assertThat(Day06.reproduce(ageCounts, 18)).isEqualTo(26);
        assertThat(Day06.reproduce(ageCounts, 80)).isEqualTo(5934);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day06.part1(input)).isEqualTo(385391);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
