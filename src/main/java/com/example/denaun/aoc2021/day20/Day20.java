package com.example.denaun.aoc2021.day20;

import org.jparsec.Parser;

class Day20 {
    private Day20() {}

    private static final Parser<Input> PARSER = Day20Parser.INPUT;

    static long part1(String input) {
        var puzzleInput = PARSER.parse(input);
        var image = puzzleInput.image()
                .enhance(puzzleInput.algorithm())
                .enhance(puzzleInput.algorithm());
        return image.countLit();
    }
}
