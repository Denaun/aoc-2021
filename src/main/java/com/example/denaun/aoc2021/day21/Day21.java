package com.example.denaun.aoc2021.day21;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.NUMBER;
import static org.jparsec.Scanners.string;

import com.example.denaun.aoc2021.parsers.AocParsers;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntSupplier;
import java.util.stream.Stream;
import org.jparsec.Parser;

class Day21 {

    private Day21() {}

    static final int ROLLS_PER_TURN = 3;
    static final int PART1_SCORE = 1000;
    static final int PART2_SCORE = 21;

    private static final Parser<GameState> PARSER = AocParsers.sepPair(
            string("Player 1 starting position: ").next(NUMBER),
            LINE_ENDING,
            string("Player 2 starting position: ").next(NUMBER),
            GameState::startingAt)
            .followedBy(LINE_ENDING);

    static int part1(String input) {
        var state = PARSER.parse(input);
        state = playOut(state,
                new MultipleDie(new DeterministicDice(), ROLLS_PER_TURN),
                PART1_SCORE);
        return state.loser(PART1_SCORE).orElseThrow().score()
                * state.turns() * ROLLS_PER_TURN;
    }

    static GameState playOut(GameState initialState, IntSupplier dice, int targetScore) {
        return Stream.iterate(initialState, state -> state.next(dice.getAsInt()))
                .dropWhile(game -> !game.hasWinner(targetScore))
                .findFirst().orElseThrow();
    }

    static long part2(String input) {
        var state = PARSER.parse(input);
        var wins = countWins(state, PART2_SCORE);
        return wins.max();
    }

    private static final Map<Integer, Integer> MULTIPLIERS = Map.of(
            3, 1,
            4, 3,
            5, 6,
            6, 7,
            7, 6,
            8, 3,
            9, 1);


    static Wins countWins(GameState state, int targetScore) {
        return countWins(state, targetScore, new HashMap<>());
    }

    private static Wins countWins(GameState state, int targetScore, Map<GameState, Wins> cache) {
        if (state.hasWinner(targetScore)) {
            if (state.player1().hasWon(targetScore)) {
                return new Wins(1, 0);
            } else {
                return new Wins(0, 1);
            }
        }
        if (cache.containsKey(state)) {
            return cache.get(state);
        }
        var wins = MULTIPLIERS.entrySet().stream()
                .map(pair -> {
                    var result = pair.getKey();
                    var multiplier = pair.getValue();
                    return countWins(state.next(result), targetScore, cache)
                            .times(multiplier);
                })
                .reduce(Wins::sum).orElseThrow();
        cache.put(state, wins);
        return wins;
    }
}
