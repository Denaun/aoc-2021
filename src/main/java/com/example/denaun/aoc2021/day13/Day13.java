package com.example.denaun.aoc2021.day13;

import com.example.denaun.aoc2021.Coordinate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.IntStream;
import org.jparsec.Parser;

class Day13 {
    private Day13() {}

    private static final Parser<Manual> PARSER = Day13Parser.INPUT;

    static int part1(String input) {
        var manual = PARSER.parse(input);
        return manual.instructions()
                .get(0)
                .apply(manual.dots())
                .size();
    }

    static String part2(String input) {
        var manual = PARSER.parse(input);
        var folded = manual.instructions().stream()
                .reduce(manual.dots(),
                        (dots, fold) -> fold.apply(dots),
                        (x, y) -> y);
        return render(folded);
    }

    static String render(Set<Coordinate> dots) {
        var width = dots.stream()
                .mapToInt(Coordinate::x)
                .max().orElseThrow()
                + 1;
        var height = dots.stream()
                .mapToInt(Coordinate::y)
                .max().orElseThrow()
                + 1;
        var lines = IntStream.range(0, height)
                .mapToObj(i -> {
                    var line = new char[width];
                    Arrays.fill(line, '.');
                    return line;
                })
                .toList();
        for (var dot : dots) {
            lines.get(dot.y())[dot.x()] = '#';
        }
        var builder = new StringBuilder(height * (width + 1));
        for (var line : lines) {
            builder.append(line);
            builder.append('\n');
        }
        return builder.toString();
    }
}
