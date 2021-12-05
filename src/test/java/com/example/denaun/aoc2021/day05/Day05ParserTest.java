package com.example.denaun.aoc2021.day05;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import org.junit.Test;

public class Day05ParserTest {
    @Test
    public void example1() {
        assertThat(Day05Parser.INPUT.parse("""
                0,9 -> 5,9
                8,0 -> 0,8
                9,4 -> 3,4
                2,2 -> 2,1
                7,0 -> 7,4
                6,4 -> 2,0
                0,9 -> 2,9
                3,4 -> 1,4
                0,0 -> 8,8
                5,5 -> 8,2
                """))
                .isEqualTo(List.of(
                        Line.of(0, 9, 5, 9),
                        Line.of(8, 0, 0, 8),
                        Line.of(9, 4, 3, 4),
                        Line.of(2, 2, 2, 1),
                        Line.of(7, 0, 7, 4),
                        Line.of(6, 4, 2, 0),
                        Line.of(0, 9, 2, 9),
                        Line.of(3, 4, 1, 4),
                        Line.of(0, 0, 8, 8),
                        Line.of(5, 5, 8, 2)));
    }
}
