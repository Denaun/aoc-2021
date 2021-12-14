package com.example.denaun.aoc2021.day14;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Day14Test extends AocTestCase {
    public Day14Test() throws IOException {
        super("day14.in");
    }

    private static final PolymerizationInstructions EXAMPLE_INPUT = new PolymerizationInstructions(
            List.of(Element.of("N"), Element.of("N"), Element.of("C"), Element.of("B")),
            Map.ofEntries(
                    Map.entry(Map.entry(Element.of("C"), Element.of("H")), Element.of("B")),
                    Map.entry(Map.entry(Element.of("H"), Element.of("H")), Element.of("N")),
                    Map.entry(Map.entry(Element.of("C"), Element.of("B")), Element.of("H")),
                    Map.entry(Map.entry(Element.of("N"), Element.of("H")), Element.of("C")),
                    Map.entry(Map.entry(Element.of("H"), Element.of("B")), Element.of("C")),
                    Map.entry(Map.entry(Element.of("H"), Element.of("C")), Element.of("B")),
                    Map.entry(Map.entry(Element.of("H"), Element.of("N")), Element.of("C")),
                    Map.entry(Map.entry(Element.of("N"), Element.of("N")), Element.of("C")),
                    Map.entry(Map.entry(Element.of("B"), Element.of("H")), Element.of("H")),
                    Map.entry(Map.entry(Element.of("N"), Element.of("C")), Element.of("B")),
                    Map.entry(Map.entry(Element.of("B"), Element.of("N")), Element.of("B")),
                    Map.entry(Map.entry(Element.of("N"), Element.of("B")), Element.of("B")),
                    Map.entry(Map.entry(Element.of("B"), Element.of("B")), Element.of("N")),
                    Map.entry(Map.entry(Element.of("B"), Element.of("C")), Element.of("B")),
                    Map.entry(Map.entry(Element.of("C"), Element.of("C")), Element.of("N")),
                    Map.entry(Map.entry(Element.of("C"), Element.of("N")), Element.of("C"))));

    @Test
    public void example1() {
        assertThat(EXAMPLE_INPUT.estimateProcess(1))
                .containsExactly(
                        Element.of("N"), 2L, Element.of("C"), 2L,
                        Element.of("B"), 2L, Element.of("H"), 1L);
        assertThat(EXAMPLE_INPUT.estimateProcess(5)
                .entrySet().stream()
                .mapToLong(Map.Entry::getValue).sum())
                        .isEqualTo(97);
        var afterTen = EXAMPLE_INPUT.estimateProcess(10);
        assertThat(afterTen.entrySet().stream()
                .mapToLong(Map.Entry::getValue).sum())
                        .isEqualTo(3073);
        assertThat(afterTen).containsAtLeast(
                Element.of("N"), 865L,
                Element.of("B"), 1749L,
                Element.of("H"), 161L);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day14.part1(input)).isEqualTo(2584);
    }

    @Test
    public void example2() {
        assertThat(EXAMPLE_INPUT.estimateProcess(40)).containsAtLeast(
                Element.of("B"), 2_192_039_569_602L,
                Element.of("H"), 3_849_876_073L);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day14.part2(input)).isEqualTo(3_816_397_135_460L);
    }
}
