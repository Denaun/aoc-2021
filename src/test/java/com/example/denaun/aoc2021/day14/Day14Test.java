package com.example.denaun.aoc2021.day14;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multisets;
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
            Map.of(
                    Element.of("B"), Map.of(
                            Element.of("B"), Element.of("N"),
                            Element.of("C"), Element.of("B"),
                            Element.of("H"), Element.of("H"),
                            Element.of("N"), Element.of("B")),
                    Element.of("C"), Map.of(
                            Element.of("B"), Element.of("H"),
                            Element.of("C"), Element.of("N"),
                            Element.of("H"), Element.of("B"),
                            Element.of("N"), Element.of("C")),
                    Element.of("H"), Map.of(
                            Element.of("B"), Element.of("C"),
                            Element.of("C"), Element.of("B"),
                            Element.of("H"), Element.of("N"),
                            Element.of("N"), Element.of("C")),
                    Element.of("N"), Map.of(
                            Element.of("B"), Element.of("B"),
                            Element.of("C"), Element.of("B"),
                            Element.of("H"), Element.of("C"),
                            Element.of("N"), Element.of("C"))));

    @Test
    public void example1() {
        assertThat(EXAMPLE_INPUT.process(1))
                .containsExactly(
                        Element.of("N"), Element.of("C"), Element.of("N"), Element.of("B"),
                        Element.of("C"), Element.of("H"), Element.of("B"))
                .inOrder();
        assertThat(EXAMPLE_INPUT.process(5).size()).isEqualTo(97);
        var afterTen = EXAMPLE_INPUT.process(10);
        assertThat(afterTen.size()).isEqualTo(3073);
        assertThat(ImmutableMultiset.copyOf(afterTen).entrySet()).containsAtLeast(
                Multisets.immutableEntry(Element.of("N"), 865),
                Multisets.immutableEntry(Element.of("B"), 1749),
                Multisets.immutableEntry(Element.of("H"), 161));
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day14.part1(input)).isEqualTo(2584);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
