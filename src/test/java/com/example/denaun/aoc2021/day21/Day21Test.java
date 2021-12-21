package com.example.denaun.aoc2021.day21;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.Optional;
import org.junit.Test;

public class Day21Test extends AocTestCase {
    public Day21Test() throws IOException {
        super("day21.in");
    }

    private static final GameState EXAMPLE_INPUT = GameState.startingAt(4, 8);

    @Test
    public void example1() {
        var dice = new DeterministicDice();
        var state = EXAMPLE_INPUT.next(dice);
        assertThat(state.player1()).isEqualTo(new PlayerState(10, 10));
        state = state.next(dice);
        assertThat(state.player2()).isEqualTo(new PlayerState(3, 3));
        state = state.next(dice);
        assertThat(state.player1()).isEqualTo(new PlayerState(4, 14));
        state = Day21.playOut(state, dice);
        assertThat(state.rolls()).isEqualTo(993);
        assertThat(state.loser()).isEqualTo(Optional.of(state.player2()));
        assertThat(state.loser().get().score()).isEqualTo(745);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day21.part1(input)).isEqualTo(913_560);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
