package com.example.denaun.aoc2021.day04;

import org.jparsec.Parser;

class Day04 {
    private Day04() {}

    private static final Parser<Bingo> PARSER = Day04Parser.INPUT;

    static int part1(String input) {
        var bingo = PARSER.parse(input);
        var games = bingo.boards().stream()
                .map(BingoBoard::from)
                .toList();
        for (var number : bingo.numbers()) {
            for (var board : games) {
                board.mark(number);
                if (board.hasWon()) {
                    return board.unmarked().sum() * number;
                }
            }
        }
        throw new AssertionError("unreachable");
    }
}
