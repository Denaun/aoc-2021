package com.example.denaun.aoc2021.day08;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import org.junit.Test;

public class Day08Test extends AocTestCase {
    public Day08Test() throws IOException {
        super("day08.in");
    }

    @Test
    public void example1() {
        assertThat(Day08ParserTest.EXAMPLE_INPUT.stream()
                .flatMap(i -> i.output().stream())
                .filter(Day08::isRecognizablePattern)
                .count())
                        .isEqualTo(26);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day08.part1(input)).isEqualTo(344);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
