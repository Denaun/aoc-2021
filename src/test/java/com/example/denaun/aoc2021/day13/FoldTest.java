package com.example.denaun.aoc2021.day13;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.Coordinate;
import java.util.Set;
import org.junit.Test;

public class FoldTest {
    static final Set<Coordinate> EXAMPLE_INPUT = Set.of(
            new Coordinate(6, 10),
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
            new Coordinate(9, 0));

    @Test
    public void example1() {
        assertThat(new Fold.Horizontal(7).apply(EXAMPLE_INPUT).size())
                .isEqualTo(17);
    }
}
