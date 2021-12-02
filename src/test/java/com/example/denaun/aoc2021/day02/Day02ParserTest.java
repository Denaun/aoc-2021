package com.example.denaun.aoc2021.day02;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class Day02ParserTest {
    @Test
    public void example1() {
        assertThat(Day02Parser.INPUT.parse("""
                forward 5
                down 5
                forward 8
                up 3
                down 8
                forward 2
                """))
                .containsExactly(
                        new Forward(5),
                        new Down(5),
                        new Forward(8),
                        new Up(3),
                        new Down(8),
                        new Forward(2));
    }
}
