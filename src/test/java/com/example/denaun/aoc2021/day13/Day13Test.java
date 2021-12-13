package com.example.denaun.aoc2021.day13;

import static com.google.common.truth.Truth.assertThat;

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
    public void example2() {
        var dots = new Fold.Horizontal(7).apply(FoldTest.EXAMPLE_INPUT);
        dots = new Fold.Vertical(5).apply(dots);
        assertThat(Day13.render(dots)).isEqualTo("""
                #####
                #...#
                #...#
                #...#
                #####
                """);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day13.part2(input)).isEqualTo("""
                #....###..####...##.###....##.####.#..#
                #....#..#.#.......#.#..#....#.#....#..#
                #....#..#.###.....#.###.....#.###..####
                #....###..#.......#.#..#....#.#....#..#
                #....#.#..#....#..#.#..#.#..#.#....#..#
                ####.#..#.#.....##..###...##..####.#..#
                """);
    }
}
