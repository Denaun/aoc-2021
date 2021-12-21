package com.example.denaun.aoc2021.day21;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static org.jparsec.Scanners.string;

import com.example.denaun.aoc2021.parsers.AocParsers;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.jparsec.Parser;

class Day21 {
    private Day21() {}

    private static final Parser<GameState> PARSER = AocParsers.sepPair(
            string("Player 1 starting position: ").next(NUMBER),
            LINE_ENDING,
            string("Player 2 starting position: ").next(NUMBER),
            GameState::startingAt)
            .followedBy(LINE_ENDING);

    static int part1(String input) {
        var state = PARSER.parse(input);
        state = playOut(state, new DeterministicDice());
        return state.loser().orElseThrow().score() * state.rolls();
    }

    static GameState playOut(GameState initialState, IntSupplier dice) {
        return Stream.iterate(initialState, state -> state.next(dice))
                .dropWhile(Predicate.not(GameState::hasWinner))
                .findFirst().orElseThrow();
    }
}
