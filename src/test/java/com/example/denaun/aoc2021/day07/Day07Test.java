package com.example.denaun.aoc2021.day07;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Day07Test extends AocTestCase {
    public Day07Test() throws IOException {
        super("day07.in");
    }

    private static final List<Integer> EXAMPLE_INPUT = List.of(16, 1, 2, 0, 4, 2, 7, 1, 2, 14);

    @Test
    public void example1() {
        assertThat(Day07.nearestPosition(EXAMPLE_INPUT)).isEqualTo(Map.entry(2, 37));
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day07.part1(input)).isEqualTo(347_011);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
