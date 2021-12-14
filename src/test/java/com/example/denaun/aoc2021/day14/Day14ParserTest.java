package com.example.denaun.aoc2021.day14;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Day14ParserTest {
    @Test
    public void example1() {
        assertThat(Day14Parser.INPUT.parse("""
                NNCB

                CH -> B
                HH -> N
                CB -> H
                NH -> C
                HB -> C
                HC -> B
                HN -> C
                NN -> C
                BH -> H
                NC -> B
                NB -> B
                BN -> B
                BB -> N
                BC -> B
                CC -> N
                CN -> C
                """))
                .isEqualTo(new PolymerizationInstructions(
                        List.of(Element.of("N"), Element.of("N"), Element.of("C"), Element.of("B")),
                        Map.ofEntries(
                                Map.entry(Map.entry(Element.of("C"), Element.of("H")),
                                        Element.of("B")),
                                Map.entry(Map.entry(Element.of("H"), Element.of("H")),
                                        Element.of("N")),
                                Map.entry(Map.entry(Element.of("C"), Element.of("B")),
                                        Element.of("H")),
                                Map.entry(Map.entry(Element.of("N"), Element.of("H")),
                                        Element.of("C")),
                                Map.entry(Map.entry(Element.of("H"), Element.of("B")),
                                        Element.of("C")),
                                Map.entry(Map.entry(Element.of("H"), Element.of("C")),
                                        Element.of("B")),
                                Map.entry(Map.entry(Element.of("H"), Element.of("N")),
                                        Element.of("C")),
                                Map.entry(Map.entry(Element.of("N"), Element.of("N")),
                                        Element.of("C")),
                                Map.entry(Map.entry(Element.of("B"), Element.of("H")),
                                        Element.of("H")),
                                Map.entry(Map.entry(Element.of("N"), Element.of("C")),
                                        Element.of("B")),
                                Map.entry(Map.entry(Element.of("B"), Element.of("N")),
                                        Element.of("B")),
                                Map.entry(Map.entry(Element.of("N"), Element.of("B")),
                                        Element.of("B")),
                                Map.entry(Map.entry(Element.of("B"), Element.of("B")),
                                        Element.of("N")),
                                Map.entry(Map.entry(Element.of("B"), Element.of("C")),
                                        Element.of("B")),
                                Map.entry(Map.entry(Element.of("C"), Element.of("C")),
                                        Element.of("N")),
                                Map.entry(Map.entry(Element.of("C"), Element.of("N")),
                                        Element.of("C")))));
    }
}
