package com.example.denaun.aoc2021.day18;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class NumberTest {
    @Test
    public void example2() {
        assertThat(Number.valueOf("[[[[[9,8],1],2],3],4]").tryReducing().get())
                .isEqualTo(Number.valueOf("[[[[0,9],2],3],4]"));
        assertThat(Number.valueOf("[7,[6,[5,[4,[3,2]]]]]").tryReducing().get())
                .isEqualTo(Number.valueOf("[7,[6,[5,[7,0]]]]"));
        assertThat(Number.valueOf("[[6,[5,[4,[3,2]]]],1]").tryReducing().get())
                .isEqualTo(Number.valueOf("[[6,[5,[7,0]]],3]"));
        assertThat(Number.valueOf("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]").tryReducing().get())
                .isEqualTo(Number.valueOf("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"));
        assertThat(Number.valueOf("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]").tryReducing().get())
                .isEqualTo(Number.valueOf("[[3,[2,[8,0]]],[9,[5,[7,0]]]]"));
    }

    @Test
    public void example3() {
        var sum = Number.sum(
                Number.valueOf("[[[[4,3],4],4],[7,[[8,4],9]]]"),
                Pair.of(1, 1));
        assertThat(sum).isEqualTo(Number.valueOf("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]"));
        assertThat(sum.fullyReduced())
                .isEqualTo(Number.valueOf("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"));
    }

    @Test
    public void example6() {
        assertThat(Number.valueOf("[[1,2],[[3,4],5]]").magnitude()).isEqualTo(143);
        assertThat(Number.valueOf("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]").magnitude()).isEqualTo(1384);
        assertThat(Number.valueOf("[[[[1,1],[2,2]],[3,3]],[4,4]]").magnitude()).isEqualTo(445);
        assertThat(Number.valueOf("[[[[3,0],[5,3]],[4,4]],[5,5]]").magnitude()).isEqualTo(791);
        assertThat(Number.valueOf("[[[[5,0],[7,4]],[5,5]],[6,6]]").magnitude()).isEqualTo(1137);
        assertThat(Number.valueOf("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")
                .magnitude()).isEqualTo(3488);
    }
}
