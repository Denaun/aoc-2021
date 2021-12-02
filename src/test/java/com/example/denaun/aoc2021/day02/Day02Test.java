package com.example.denaun.aoc2021.day02;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day02Test extends AocTestCase {
    public Day02Test() throws IOException {
        super("day02.in");
    }

    @Test
    public void example1() {
        assertThat(Day02.finalPosition(List.of(
                new Forward(5),
                new Down(5),
                new Forward(8),
                new Up(3),
                new Down(8),
                new Forward(2))))
                        .isEqualTo(new Position(15, 10));
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day02.part1(input)).isEqualTo(2117664);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
