package com.example.denaun.aoc2021.day17;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.stream.IntStream;
import org.jparsec.Parser;

class Day17 {
    private Day17() {}

    private static final Parser<Area> PARSER = Day17Parser.AREA;

    static int part1(String input) {
        var area = PARSER.parse(input);
        return backtrackHeight(area.bottom());
    }

    static long part2(String input) {
        var area = PARSER.parse(input);
        return countVelocities(area);
    }

    static int backtrackHeight(int depth) {
        // We need to pass through 0, and have a velocity of maximum `|depth|`
        // at the next step so that we don't get out. Decrease by one to get the
        // previous step.
        var initialVelocity = -depth - 1;
        return (initialVelocity * (initialVelocity + 1)) / 2;
    }

    static long countVelocities(Area area) {
        return IntStream.rangeClosed(1, area.right())
                .mapToLong(vx -> IntStream.rangeClosed(area.bottom(), -area.bottom() - 1)
                        .filter(vy -> reachesTarget(vx, vy, area))
                        .count())
                .sum();
    }

    private static boolean reachesTarget(int vx, int vy, Area area) {
        checkArgument(vx >= 0);
        int x = 0;
        int y = 0;
        while (x <= area.right() && y >= area.bottom()) {
            x += vx;
            y += vy;
            if (area.isInside(x, y)) {
                return true;
            }
            vx = Math.max(0, vx - 1);
            vy -= 1;
        }
        return false;
    }
}
