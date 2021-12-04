package com.example.denaun.aoc2021.day04;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day04Test extends AocTestCase {
    public Day04Test() throws IOException {
        super("day04.in");
    }

    @Test
    public void example1() {
        var loser = BingoBoard.from(Board.of(
                22, 13, 17, 11, 0,
                8, 2, 23, 4, 24,
                21, 9, 14, 16, 7,
                6, 10, 3, 18, 5,
                1, 12, 20, 15, 19));
        assertThat(loser.hasWon()).isFalse();
        var winner = BingoBoard.from(Board.of(
                14, 21, 17, 24, 4,
                10, 16, 15, 9, 19,
                18, 8, 23, 26, 20,
                22, 11, 13, 6, 5,
                2, 0, 12, 3, 7));
        assertThat(winner.hasWon()).isFalse();
        for (var number : List.of(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21)) {
            loser.mark(number);
            assertThat(loser.hasWon()).isFalse();
            winner.mark(number);
            assertThat(winner.hasWon()).isFalse();
        }
        loser.mark(24);
        assertThat(loser.hasWon()).isFalse();
        winner.mark(24);
        assertThat(winner.hasWon()).isTrue();
        assertThat(winner.unmarked().sum()).isEqualTo(188);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day04.part1(input)).isEqualTo(44088);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
