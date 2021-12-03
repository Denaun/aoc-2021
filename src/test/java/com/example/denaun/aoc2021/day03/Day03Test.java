package com.example.denaun.aoc2021.day03;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day03Test extends AocTestCase {
    public Day03Test() throws IOException {
        super("day03.in");
    }

    private static final DiagnosticReport EXAMPLE_INPUT = new DiagnosticReport(
            5,
            List.of(0b00100,
                    0b11110,
                    0b10110,
                    0b10111,
                    0b10101,
                    0b01111,
                    0b00111,
                    0b11100,
                    0b10000,
                    0b11001,
                    0b00010,
                    0b01010));

    @Test
    public void example1() {
        assertThat(EXAMPLE_INPUT.mostCommonBits()).isEqualTo(0b10110);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day03.part1(input)).isEqualTo(1307354);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
