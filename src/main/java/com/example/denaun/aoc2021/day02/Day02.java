package com.example.denaun.aoc2021.day02;

import java.util.List;
import org.jparsec.Parser;

class Day02 {
    private Day02() {}

    private static final Parser<List<Direction>> PARSER = Day02Parser.INPUT;

    static int part1(String input) {
        var directions = PARSER.parse(input);
        var position = applyDirections(Position.center(), directions);
        return position.horizontal() * position.depth();
    }

    static int part2(String input) {
        var directions = PARSER.parse(input);
        var position = applyDirections(Trajectory.center(), directions);
        return position.horizontal() * position.depth();
    }

    static <T extends Movable<T>> T applyDirections(T movable, List<Direction> directions) {
        for (var direction : directions) {
            movable = direction.move(movable);
        }
        return movable;
    }
}
