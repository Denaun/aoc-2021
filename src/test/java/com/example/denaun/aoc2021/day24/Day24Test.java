package com.example.denaun.aoc2021.day24;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import org.junit.Test;

public class Day24Test extends AocTestCase {
    public Day24Test() throws IOException {
        super("day24.in");
    }


    @Test
    @Override
    public void part1() {
        assertThat(Day24.part1(input)).isEqualTo(99_429_795_993_929L);
    }


    @Test
    @Override
    public void part2() {
        assertThat(Day24.part2(input)).isEqualTo(18_113_181_571_611L);
    }
}
