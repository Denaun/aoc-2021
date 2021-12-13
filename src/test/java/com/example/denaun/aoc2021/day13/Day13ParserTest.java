package com.example.denaun.aoc2021.day13;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.Coordinate;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class Day13ParserTest {
    @Test
    public void example1() {
        assertThat(Day13Parser.INPUT.parse("""
                6,10
                0,14
                9,10
                0,3
                10,4
                4,11
                6,0
                6,12
                4,1
                0,13
                10,12
                3,4
                3,0
                8,4
                1,10
                2,14
                8,10
                9,0

                fold along y=7
                fold along x=5
                """))
                .isEqualTo(new Manual(
                        Set.of(new Coordinate(6, 10),
                                new Coordinate(0, 14),
                                new Coordinate(9, 10),
                                new Coordinate(0, 3),
                                new Coordinate(10, 4),
                                new Coordinate(4, 11),
                                new Coordinate(6, 0),
                                new Coordinate(6, 12),
                                new Coordinate(4, 1),
                                new Coordinate(0, 13),
                                new Coordinate(10, 12),
                                new Coordinate(3, 4),
                                new Coordinate(3, 0),
                                new Coordinate(8, 4),
                                new Coordinate(1, 10),
                                new Coordinate(2, 14),
                                new Coordinate(8, 10),
                                new Coordinate(9, 0)),
                        List.of(new Fold.Horizontal(7),
                                new Fold.Vertical(5))));
    }
}
