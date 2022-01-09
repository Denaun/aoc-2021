package com.example.denaun.aoc2021.day25;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import org.junit.Test;

public class Day25Test extends AocTestCase {
    public Day25Test() throws IOException {
        super("day25.in");
    }

    private static final HerdMap EXAMPLE_INPUT = Day25Parser.INPUT.parse("""
            v...>>.vv>
            .vv>>.vv..
            >>.>v>...v
            >>v>>.>.v.
            v>v.vv.v..
            >.>>..v...
            .vv..>.>v.
            v.v..>>v.v
            ....v..v.>
            """);


    @Test
    public void example1() {
        assertThat(Day25.stepsToEverythingStuck(EXAMPLE_INPUT)).isEqualTo(58);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day25.part1(input)).isEqualTo(560);
    }


    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
