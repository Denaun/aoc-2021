package com.example.denaun.aoc2021.day02;

import java.util.List;
import org.jparsec.Parser;

class Day02 {
    private Day02() {}

    private static final Parser<List<Direction>> PARSER = Day02Parser.INPUT;

    static int part1(String input) {
        var directions = PARSER.parse(input);
        var position = finalPosition(directions);
        return position.horizontal() * position.depth();
    }

    static Position finalPosition(List<Direction> directions) {
        var position = new Position(0, 0);
        for (var direction : directions) {
            position = direction.move(position);
        }
        return position;
    }
}
