package com.example.denaun.aoc2021.day03;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import org.junit.Test;

public class Day03ParserTest {
    @Test
    public void example1() {
        assertThat(Day03Parser.INPUT.parse("""
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010
                """))
                .isEqualTo(new DiagnosticReport(
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
                                0b01010)));
    }
}
