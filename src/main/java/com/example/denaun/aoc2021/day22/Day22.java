package com.example.denaun.aoc2021.day22;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import java.util.List;
import org.jparsec.Parser;

class Day22 {
    private Day22() {}

    private static final Parser<List<RebootStep>> PARSER = Day22Parser.INPUT;
    static final Cuboid<Integer> PART1_LIMIT = Cuboid.allClosed(-50, 50);

    static int part1(String input) {
        var steps = PARSER.parse(input);
        var cuboids = CuboidSet.<Integer>create();
        steps.stream()
                .flatMap(step -> step.cuboid().intersect(PART1_LIMIT)
                        .map(cuboid -> new RebootStep(step.state(), cuboid))
                        .stream())
                .forEach(step -> step.executeOn(cuboids));
        return cuboids.stream().mapToInt(Day22::cuboidSize).sum();
    }

    static int cuboidSize(Cuboid<Integer> cuboid) {
        return ContiguousSet.create(cuboid.x(), DiscreteDomain.integers()).size()
                * ContiguousSet.create(cuboid.y(), DiscreteDomain.integers()).size()
                * ContiguousSet.create(cuboid.z(), DiscreteDomain.integers()).size();
    }
}
