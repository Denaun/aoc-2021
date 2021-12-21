package com.example.denaun.aoc2021.day21;

import static com.google.common.base.Preconditions.checkState;

import java.util.Optional;

record GameState(PlayerState player1, PlayerState player2, int turns) {

    static GameState startingAt(int player1, int player2) {
        return new GameState(PlayerState.startingAt(player1), PlayerState.startingAt(player2), 0);
    }

    GameState next(int steps) {
        var nextTurns = turns + 1;
        if (turns % 2 == 0) {
            return new GameState(player1.advanced(steps), player2, nextTurns);
        } else {
            return new GameState(player1, player2.advanced(steps), nextTurns);
        }
    }

    boolean hasWinner(int targetScore) {
        return player1.hasWon(targetScore) || player2.hasWon(targetScore);
    }

    Optional<PlayerState> winner(int targetScore) {
        checkState(!player1.hasWon(targetScore)
                || !player2.hasWon(targetScore));
        if (player1.hasWon(targetScore)) {
            return Optional.of(player1);
        }
        if (player2.hasWon(targetScore)) {
            return Optional.of(player2);
        }
        return Optional.empty();
    }

    Optional<PlayerState> loser(int targetScore) {
        checkState(player1.hasWon(targetScore)
                || player2.hasWon(targetScore));
        if (!player1.hasWon(targetScore)) {
            return Optional.of(player1);
        }
        if (!player2.hasWon(targetScore)) {
            return Optional.of(player2);
        }
        return Optional.empty();
    }
}
