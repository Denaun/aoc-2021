package com.example.denaun.aoc2021.day20;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import org.junit.Test;

public class Day20Test extends AocTestCase {
    public Day20Test() throws IOException {
        super("day20.in");
    }

    private static final Input EXAMPLE_INPUT = Day20Parser.INPUT
            .parse("""
                    ..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#

                    #..#.
                    #....
                    ##..#
                    ..#..
                    ..###
                    """);

    @Test
    public void example1() {
        var image = EXAMPLE_INPUT.image()
                .enhance(EXAMPLE_INPUT.algorithm())
                .enhance(EXAMPLE_INPUT.algorithm());
        assertThat(image.toString()).isEqualTo("""
                .......#.
                .#..#.#..
                #.#...###
                #...##.#.
                #.....#.#
                .#.#####.
                ..#.#####
                ...##.##.
                ....###..""");
        assertThat(image.countLit()).isEqualTo(35);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day20.part1(input)).isEqualTo(4928);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
