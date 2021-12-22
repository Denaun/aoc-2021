package com.example.denaun.aoc2021.day22;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class Day22Test extends AocTestCase {
    public Day22Test() throws IOException {
        super("day22.in");
    }

    private static final List<RebootStep> EXAMPLE_INPUT = List.of(
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-20..26,y=-36..17,z=-47..7")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-20..33,y=-21..23,z=-26..28")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-22..28,y=-29..23,z=-38..16")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-46..7,y=-6..46,z=-50..-1")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-49..1,y=-3..46,z=-24..28")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=2..47,y=-22..22,z=-23..27")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-27..23,y=-28..26,z=-21..29")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-39..5,y=-6..47,z=-3..44")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-30..21,y=-8..43,z=-13..34")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-22..26,y=-27..20,z=-29..19")),
            new RebootStep(State.OFF,
                    Day22Parser.CUBOID.parse("x=-48..-32,y=26..41,z=-47..-37")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-12..35,y=6..50,z=-50..-2")),
            new RebootStep(State.OFF,
                    Day22Parser.CUBOID.parse("x=-48..-32,y=-32..-16,z=-15..-5")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-18..26,y=-33..15,z=-7..46")),
            new RebootStep(State.OFF,
                    Day22Parser.CUBOID.parse("x=-40..-22,y=-38..-28,z=23..41")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-16..35,y=-41..10,z=-47..6")),
            new RebootStep(State.OFF,
                    Day22Parser.CUBOID.parse("x=-32..-23,y=11..30,z=-14..3")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-49..-5,y=-3..45,z=-29..18")),
            new RebootStep(State.OFF,
                    Day22Parser.CUBOID.parse("x=18..30,y=-20..-8,z=-3..13")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-41..9,y=-7..43,z=-33..15")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=-54112..-39298,y=-85059..-49293,z=-27449..7877")),
            new RebootStep(State.ON,
                    Day22Parser.CUBOID.parse("x=967..23432,y=45373..81175,z=27513..53682")));

    @Test
    public void example1() {
        var cuboids = CuboidSet.<Integer>create();
        cuboids.add(Cuboid.allClosed(10, 12));
        assertThat(cuboids.stream()
                .mapToInt(Day22::cuboidSize)
                .sum()).isEqualTo(27);
        cuboids.add(Cuboid.allClosed(11, 13));
        assertThat(cuboids.stream()
                .mapToInt(Day22::cuboidSize)
                .sum()).isEqualTo(27 + 19);
        cuboids.remove(Cuboid.allClosed(9, 11));
        assertThat(cuboids.stream()
                .mapToInt(Day22::cuboidSize)
                .sum()).isEqualTo(27 + 19 - 8);
        cuboids.add(Cuboid.allClosed(10, 10));
        assertThat(cuboids.stream()
                .mapToInt(Day22::cuboidSize)
                .sum()).isEqualTo(39);
    }

    @Test
    public void example2() {
        var cuboids = CuboidSet.<Integer>create();
        EXAMPLE_INPUT.stream()
                .flatMap(step -> step.cuboid().intersect(Day22.PART1_LIMIT)
                        .map(cuboid -> new RebootStep(step.state(), cuboid))
                        .stream())
                .forEach(step -> step.executeOn(cuboids));
        assertThat(cuboids.stream()
                .mapToInt(Day22::cuboidSize).sum())
                        .isEqualTo(590_784);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day22.part1(input)).isEqualTo(644_257);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
