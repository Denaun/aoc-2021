package com.example.denaun.aoc2021.day24;

import static com.example.denaun.aoc2021.day24.Stack.pop;
import static com.example.denaun.aoc2021.day24.Stack.push;
import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import org.junit.Test;

public class StackTest {
    @Test
    public void combine() {
        var stack = List.of(
                push(0),
                push(3),
                push(8),
                pop(-5),
                push(13),
                push(9),
                push(6),
                pop(-14),
                pop(-8),
                push(2),
                pop(0),
                pop(-5),
                pop(-9),
                pop(-1));

        var combined = stack.stream().reduce(Stack::combine);

        assertThat(combined.orElseThrow().getAsConstraints())
                .containsExactly(
                        new Constraint(3, 2, 3),
                        new Constraint(-8, 6, 7),
                        new Constraint(1, 5, 8),
                        new Constraint(2, 9, 10),
                        new Constraint(8, 4, 11),
                        new Constraint(-6, 1, 12),
                        new Constraint(-1, 0, 13));
    }
}
