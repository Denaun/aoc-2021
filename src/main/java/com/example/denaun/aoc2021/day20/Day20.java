package com.example.denaun.aoc2021.day20;

import java.util.stream.Stream;
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

    static long part2(String input) {
        var puzzleInput = PARSER.parse(input);
        var image = Stream.iterate(
                puzzleInput.image(),
                img -> img.enhance(puzzleInput.algorithm()))
                .skip(50)
                .findFirst().orElseThrow();
        return image.countLit();
    }
}
