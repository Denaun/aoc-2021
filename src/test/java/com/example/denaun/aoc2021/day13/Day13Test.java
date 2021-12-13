package com.example.denaun.aoc2021.day13;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import org.junit.Test;

public class Day13Test extends AocTestCase {
    public Day13Test() throws IOException {
        super("day13.in");
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day13.part1(input)).isEqualTo(706);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
