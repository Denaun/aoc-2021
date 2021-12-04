package com.example.denaun.aoc2021.day04;

import java.util.BitSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BingoBoard {
    private final Board board;
    private final Map<Integer, Integer> locations;
    private final BitSet marked;

    static BingoBoard from(Board board) {
        return new BingoBoard(board);
    }

    private BingoBoard(Board board) {
        this.board = board;
        locations = IntStream.range(0, board.numbers().size())
                .boxed()
                .collect(Collectors.toMap(
                        board.numbers()::get,
                        Function.identity()));
        marked = new BitSet(locations.size());
    }

    void mark(Integer number) {
        if (locations.containsKey(number)) {
            marked.set(locations.get(number));
        }
    }

    boolean hasWon() {
        return IntStream.range(0, Board.EDGE_SIZE)
                .anyMatch(i -> marked.nextClearBit(i * Board.EDGE_SIZE) >= (i + 1) * Board.EDGE_SIZE
                        || (IntStream.range(0, Board.EDGE_SIZE)
                                .allMatch(j -> marked.get(j * Board.EDGE_SIZE + i))));
    }

    IntStream unmarked() {
        return IntStream.range(0, board.numbers().size())
                .filter(i -> !marked.get(i))
                .map(i -> board.numbers().get(i));
    }
}
