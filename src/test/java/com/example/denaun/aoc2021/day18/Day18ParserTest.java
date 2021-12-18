package com.example.denaun.aoc2021.day18;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class Day18ParserTest {
    @Test
    public void example1() {
        assertThat(Day18Parser.number().parse("[1,2]")).isEqualTo(Pair.of(1, 2));
        assertThat(Day18Parser.number().parse("[[1,2],3]")).isEqualTo(Pair.of(Pair.of(1, 2), 3));
        assertThat(Day18Parser.number().parse("[9,[8,7]]")).isEqualTo(Pair.of(9, Pair.of(8, 7)));
        assertThat(Day18Parser.number().parse("[[1,9],[8,5]]"))
                .isEqualTo(Pair.of(Pair.of(1, 9), Pair.of(8, 5)));
        assertThat(Day18Parser.number().parse("[[[[1,2],[3,4]],[[5,6],[7,8]]],9]"))
                .isEqualTo(Pair.of(Pair.of(Pair.of(Pair.of(1, 2), Pair.of(3, 4)),
                        Pair.of(Pair.of(5, 6), Pair.of(7, 8))), 9));
        assertThat(Day18Parser.number().parse("[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]"))
                .isEqualTo(Pair.of(Pair.of(Pair.of(9, Pair.of(3, 8)), Pair.of(Pair.of(0, 9), 6)),
                        Pair.of(Pair.of(Pair.of(3, 7), Pair.of(4, 9)), 3)));
        assertThat(Day18Parser.number()
                .parse("[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]"))
                        .isEqualTo(Pair.of(
                                Pair.of(Pair.of(Pair.of(1, 3), Pair.of(5, 3)),
                                        Pair.of(Pair.of(1, 3), Pair.of(8, 7))),
                                Pair.of(Pair.of(Pair.of(4, 9), Pair.of(6, 9)),
                                        Pair.of(Pair.of(8, 2), Pair.of(7, 3)))));
    }
}
