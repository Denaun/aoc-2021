package com.example.denaun.aoc2021.day21;

record Wins(long player1, long player2) {
    Wins sum(Wins other) {
        return new Wins(player1 + other.player1, player2 + other.player2);
    }

    Wins times(int times) {
        return new Wins(player1 * times, player2 * times);
    }

    long max() {
        return Long.max(player1, player2);
    }
}
