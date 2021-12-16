package com.example.denaun.aoc2021.day16;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import com.google.common.truth.Correspondence;
import java.io.IOException;
import org.junit.Test;

public class Day16Test extends AocTestCase {
    public Day16Test() throws IOException {
        super("day16.in");
    }

    @Test
    public void example4() {
        assertThat(Day16.flatten(Day16Parser.INPUT.parse("8A004A801A8002F478\n")).toList())
                .comparingElementsUsing(Correspondence.transforming(Packet::version, "version"))
                .containsExactly(4, 1, 5, 6);
    }

    @Test
    public void example5() {
        assertThat(Day16.flatten(Day16Parser.INPUT.parse("620080001611562C8802118E34\n"))
                .mapToInt(Packet::version)
                .sum())
                        .isEqualTo(12);
    }

    @Test
    public void example6() {
        assertThat(Day16.flatten(Day16Parser.INPUT.parse("C0015000016115A2E0802F182340\n"))
                .mapToInt(Packet::version)
                .sum())
                        .isEqualTo(23);
    }

    @Test
    public void example7() {
        assertThat(Day16.flatten(Day16Parser.INPUT.parse("A0016C880162017C3686B18A3D4780\n"))
                .mapToInt(Packet::version)
                .sum())
                        .isEqualTo(31);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day16.part1(input)).isEqualTo(938);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
