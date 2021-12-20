package com.example.denaun.aoc2021.day20;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.stream.Stream;
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
        image = Stream.iterate(
                EXAMPLE_INPUT.image(),
                img -> img.enhance(EXAMPLE_INPUT.algorithm()))
                .skip(50)
                .findFirst().orElseThrow();
        assertThat(image.countLit()).isEqualTo(3351);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day20.part1(input)).isEqualTo(4928);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day20.part2(input)).isEqualTo(16_605);
    }
}
