package com.example.denaun.aoc2021.day21;

import static com.google.common.base.Preconditions.checkState;

import java.util.Optional;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

record GameState(PlayerState player1, PlayerState player2, int turn) {

    private static final int ROLLS_PER_TURN = 3;

    static GameState startingAt(int player1, int player2) {
        return new GameState(PlayerState.startingAt(player1), PlayerState.startingAt(player2), 0);
    }

    GameState next(IntSupplier dice) {
        var nextTurn = turn + 1;
        var steps = IntStream.generate(dice::getAsInt).limit(ROLLS_PER_TURN).sum();
        if (turn % 2 == 0) {
            return new GameState(player1.advanced(steps), player2, nextTurn);
        } else {
            return new GameState(player1, player2.advanced(steps), nextTurn);
        }
    }

    int rolls() {
        return turn * ROLLS_PER_TURN;
    }

    boolean hasWinner() {
        return player1.hasWon() || player2.hasWon();
    }

    Optional<PlayerState> winner() {
        checkState(!player1.hasWon() || !player2.hasWon());
        if (player1.hasWon()) {
            return Optional.of(player1);
        }
        if (player2.hasWon()) {
            return Optional.of(player2);
        }
        return Optional.empty();
    }

    Optional<PlayerState> loser() {
        checkState(player1.hasWon() || player2.hasWon());
        if (!player1.hasWon()) {
            return Optional.of(player1);
        }
        if (!player2.hasWon()) {
            return Optional.of(player2);
        }
        return Optional.empty();
    }
}
