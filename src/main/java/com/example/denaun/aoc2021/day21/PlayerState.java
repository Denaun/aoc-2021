package com.example.denaun.aoc2021.day21;

import static com.google.common.base.Preconditions.checkArgument;

record PlayerState(int position, int score) {

    private static final int TARGET_SCORE = 1000;
    private static final int BOARD_SIZE = 10;

    static PlayerState startingAt(int position) {
        return new PlayerState(position, 0);
    }

    PlayerState {
        checkArgument(position >= 1 && position <= BOARD_SIZE);
    }

    boolean hasWon() {
        return score >= TARGET_SCORE;
    }

    PlayerState advanced(int steps) {
        var newPosition = 1 + ((position - 1) + steps) % BOARD_SIZE;
        var newScore = score + newPosition;
        return new PlayerState(newPosition, newScore);
    }
}
