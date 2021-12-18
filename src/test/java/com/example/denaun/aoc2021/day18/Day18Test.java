package com.example.denaun.aoc2021.day18;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day18Test extends AocTestCase {
    public Day18Test() throws IOException {
        super("day18.in");
    }

    @Test
    public void example4() {
        assertThat(Day18.sumAll(List.of(
                Pair.of(1, 1),
                Pair.of(2, 2),
                Pair.of(3, 3),
                Pair.of(4, 4))))
                        .isEqualTo(Number.valueOf("[[[[1,1],[2,2]],[3,3]],[4,4]]"));
        assertThat(Day18.sumAll(List.of(
                Pair.of(1, 1),
                Pair.of(2, 2),
                Pair.of(3, 3),
                Pair.of(4, 4),
                Pair.of(5, 5))))
                        .isEqualTo(Number.valueOf("[[[[3,0],[5,3]],[4,4]],[5,5]]"));
        assertThat(Day18.sumAll(List.of(
                Pair.of(1, 1),
                Pair.of(2, 2),
                Pair.of(3, 3),
                Pair.of(4, 4),
                Pair.of(5, 5),
                Pair.of(6, 6))))
                        .isEqualTo(Number.valueOf("[[[[5,0],[7,4]],[5,5]],[6,6]]"));
    }

    @Test
    public void example5() {
        assertThat(Day18.sumAll(List.of(
                Number.valueOf("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]"),
                Number.valueOf("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]"),
                Number.valueOf("[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]"),
                Number.valueOf("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]"),
                Number.valueOf("[7,[5,[[3,8],[1,4]]]]"),
                Number.valueOf("[[2,[2,2]],[8,[8,1]]]"),
                Number.valueOf("[2,9]"),
                Number.valueOf("[1,[[[9,3],9],[[9,0],[0,7]]]]"),
                Number.valueOf("[[[5,[7,4]],7],1]"),
                Number.valueOf("[[[[4,2],2],6],[8,7]]"))))
                        .isEqualTo(Number.valueOf(
                                "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"));
    }

    @Test
    public void example7() {
        assertThat(Day18.sumAll(List.of(
                Number.valueOf("[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]"),
                Number.valueOf("[[[5,[2,8]],4],[5,[[9,9],0]]]"),
                Number.valueOf("[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]"),
                Number.valueOf("[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]"),
                Number.valueOf("[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]"),
                Number.valueOf("[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]"),
                Number.valueOf("[[[[5,4],[7,7]],8],[[8,3],8]]"),
                Number.valueOf("[[9,3],[[9,9],[6,[4,9]]]]"),
                Number.valueOf("[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]"),
                Number.valueOf("[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]")))
                .magnitude())
                        .isEqualTo(4140);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day18.part1(input)).isEqualTo(3699);
    }

    @Test
    public void example8() {
        assertThat(Day18.largestSum(List.of(
                Number.valueOf("[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]"),
                Number.valueOf("[[[5,[2,8]],4],[5,[[9,9],0]]]"),
                Number.valueOf("[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]"),
                Number.valueOf("[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]"),
                Number.valueOf("[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]"),
                Number.valueOf("[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]"),
                Number.valueOf("[[[[5,4],[7,7]],8],[[8,3],8]]"),
                Number.valueOf("[[9,3],[[9,9],[6,[4,9]]]]"),
                Number.valueOf("[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]"),
                Number.valueOf("[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]")))
                .magnitude())
                        .isEqualTo(3993);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day18.part2(input)).isEqualTo(4735);
    }
}
