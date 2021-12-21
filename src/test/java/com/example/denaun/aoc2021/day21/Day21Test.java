package com.example.denaun.aoc2021.day21;

import static com.google.common.truth.Truth.assertThat;

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
        var dice = new MultipleDie(new DeterministicDice(), Day21.ROLLS_PER_TURN);
        var state = EXAMPLE_INPUT.next(dice.getAsInt());
        assertThat(state.player1()).isEqualTo(new PlayerState(10, 10));
        state = state.next(dice.getAsInt());
        assertThat(state.player2()).isEqualTo(new PlayerState(3, 3));
        state = state.next(dice.getAsInt());
        assertThat(state.player1()).isEqualTo(new PlayerState(4, 14));
        state = Day21.playOut(state, dice, Day21.PART1_SCORE);
        assertThat(state.turns() * Day21.ROLLS_PER_TURN).isEqualTo(993);
        assertThat(state.loser(Day21.PART1_SCORE)).isEqualTo(Optional.of(state.player2()));
        assertThat(state.loser(Day21.PART1_SCORE).get().score()).isEqualTo(745);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day21.part1(input)).isEqualTo(913_560);
    }

    @Test
    public void example2() {
        assertThat(Day21.countWins(EXAMPLE_INPUT, Day21.PART2_SCORE))
                .isEqualTo(new Wins(444_356_092_776_315L, 341_960_390_180_808L));
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day21.part2(input)).isEqualTo(110_271_560_863_819L);
    }
}
