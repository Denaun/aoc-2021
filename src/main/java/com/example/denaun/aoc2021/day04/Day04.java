package com.example.denaun.aoc2021.day04;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toSet;

import com.google.common.collect.Streams;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import org.jparsec.Parser;

class Day04 {
    private Day04() {}

    private static final Parser<Bingo> PARSER = Day04Parser.INPUT;

    static int part1(String input) {
        var bingo = PARSER.parse(input);
        var games = bingo.boards().stream()
                .map(BingoBoard::from)
                .collect(toSet());
        return winners(bingo.numbers(), games).findFirst().orElseThrow();
    }

    static int part2(String input) {
        var bingo = PARSER.parse(input);
        var games = bingo.boards().stream()
                .map(BingoBoard::from)
                .collect(toSet());
        return Streams.findLast(winners(bingo.numbers(), games)).orElseThrow();
    }

    static IntStream winners(List<Integer> numbers, Set<BingoBoard> boards) {
        checkArgument(boards.stream().noneMatch(BingoBoard::hasWon));
        return numbers.stream().flatMapToInt(number -> {
            for (var board : boards) {
                board.mark(number);
            }
            var winners = boards.stream().filter(BingoBoard::hasWon).toList();
            boards.removeAll(winners);
            return winners.stream()
                    .mapToInt(board -> board.unmarked().sum() * number);
        });
    }
}
