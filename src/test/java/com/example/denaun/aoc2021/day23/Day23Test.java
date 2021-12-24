package com.example.denaun.aoc2021.day23;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.Test;

public class Day23Test extends AocTestCase {
    public Day23Test() throws IOException {
        super("day23.in");
    }

    static final List<Room> EXAMPLE_INPUT = List.of(
            new Room(Amphipod.AMBER,
                    List.of(Optional.of(Amphipod.BRONZE), Optional.of(Amphipod.AMBER))),
            new Room(Amphipod.BRONZE,
                    List.of(Optional.of(Amphipod.COPPER), Optional.of(Amphipod.DESERT))),
            new Room(Amphipod.COPPER,
                    List.of(Optional.of(Amphipod.BRONZE), Optional.of(Amphipod.COPPER))),
            new Room(Amphipod.DESERT,
                    List.of(Optional.of(Amphipod.DESERT), Optional.of(Amphipod.AMBER))));

    @Test
    public void example1() {
        assertThat(Day23.minimumCost(new Burrow(EXAMPLE_INPUT)))
                .isEqualTo(12_521);
    }


    @Test
    @Override
    public void part1() {
        assertThat(Day23.part1(input)).isEqualTo(15_412);
    }

    @Test
    public void example2() {
        assertThat(Day23.minimumCost(new Burrow(Day23.unfold(EXAMPLE_INPUT))))
                .isEqualTo(44_169);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day23.part2(input)).isEqualTo(52_358);
    }
}
