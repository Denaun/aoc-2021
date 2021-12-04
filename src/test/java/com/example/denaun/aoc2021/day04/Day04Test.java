package com.example.denaun.aoc2021.day04;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;

public class Day04Test extends AocTestCase {
    public Day04Test() throws IOException {
        super("day04.in");
    }

    @Test
    public void example1() {
        var loser = BingoBoard.from(Board.of(
                3, 15, 0, 2, 22,
                9, 18, 13, 17, 5,
                19, 8, 7, 25, 23,
                20, 11, 10, 24, 4,
                14, 21, 16, 12, 6));
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
        for (var number : List.of(10, 16)) {
            loser.mark(number);
            assertThat(loser.hasWon()).isFalse();
        }
        loser.mark(13);
        assertThat(loser.hasWon()).isTrue();
        assertThat(loser.unmarked().sum()).isEqualTo(148);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day04.part1(input)).isEqualTo(44088);
    }

    @Test
    public void multipleWinners() {
        var numbers = IntStream.rangeClosed(1, 26).boxed().toList();
        var upTo25 = IntStream.rangeClosed(1, 25).boxed().toList();
        var upTo26 = IntStream.rangeClosed(2, 26).boxed().toList();
        var games = Sets.newHashSet(
                BingoBoard.from(new Board(upTo25)),
                BingoBoard.from(new Board(upTo26)),
                BingoBoard.from(new Board(upTo25)));
        assertThat(Day04.winners(numbers, games).toArray()).asList()
                .containsExactly(1550, 1550, 1980).inOrder();
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day04.part2(input)).isEqualTo(23670);
    }
}
