package com.example.denaun.aoc2021.day08;

import static com.google.common.truth.Truth.assertThat;

import java.util.EnumSet;
import java.util.List;
import org.junit.Test;

public class Day08ParserTest {
    static List<DisplayObservation> EXAMPLE_INPUT = List.of(
            new DisplayObservation(
                    List.of(
                            EnumSet.of(Signal.B, Signal.E),
                            EnumSet.of(Signal.C, Signal.F, Signal.B, Signal.E, Signal.G,
                                    Signal.A, Signal.D),
                            EnumSet.of(Signal.C, Signal.B, Signal.D, Signal.G, Signal.E,
                                    Signal.F),
                            EnumSet.of(Signal.F, Signal.G, Signal.A, Signal.E, Signal.C,
                                    Signal.D),
                            EnumSet.of(Signal.C, Signal.G, Signal.E, Signal.B),
                            EnumSet.of(Signal.F, Signal.D, Signal.C, Signal.G, Signal.E),
                            EnumSet.of(Signal.A, Signal.G, Signal.E, Signal.B, Signal.F,
                                    Signal.D),
                            EnumSet.of(Signal.F, Signal.E, Signal.C, Signal.D, Signal.B),
                            EnumSet.of(Signal.F, Signal.A, Signal.B, Signal.C, Signal.D),
                            EnumSet.of(Signal.E, Signal.D, Signal.B)),
                    List.of(
                            EnumSet.of(Signal.F, Signal.D, Signal.G, Signal.A, Signal.C,
                                    Signal.B, Signal.E),
                            EnumSet.of(Signal.C, Signal.E, Signal.F, Signal.D, Signal.B),
                            EnumSet.of(Signal.C, Signal.E, Signal.F, Signal.B, Signal.G,
                                    Signal.D),
                            EnumSet.of(Signal.G, Signal.C, Signal.B, Signal.E))),
            new DisplayObservation(
                    List.of(
                            EnumSet.of(Signal.E, Signal.D, Signal.B, Signal.F, Signal.G,
                                    Signal.A),
                            EnumSet.of(Signal.B, Signal.E, Signal.G, Signal.C, Signal.D),
                            EnumSet.of(Signal.C, Signal.B, Signal.G),
                            EnumSet.of(Signal.G, Signal.C),
                            EnumSet.of(Signal.G, Signal.C, Signal.A, Signal.D, Signal.E,
                                    Signal.B, Signal.F),
                            EnumSet.of(Signal.F, Signal.B, Signal.G, Signal.D, Signal.E),
                            EnumSet.of(Signal.A, Signal.C, Signal.B, Signal.G, Signal.F,
                                    Signal.D),
                            EnumSet.of(Signal.A, Signal.B, Signal.C, Signal.D, Signal.E),
                            EnumSet.of(Signal.G, Signal.F, Signal.C, Signal.B, Signal.E,
                                    Signal.D),
                            EnumSet.of(Signal.G, Signal.F, Signal.E, Signal.C)),
                    List.of(
                            EnumSet.of(Signal.F, Signal.C, Signal.G, Signal.E, Signal.D,
                                    Signal.B),
                            EnumSet.of(Signal.C, Signal.G, Signal.B),
                            EnumSet.of(Signal.D, Signal.G, Signal.E, Signal.B, Signal.A,
                                    Signal.C, Signal.F),
                            EnumSet.of(Signal.G, Signal.C))),
            new DisplayObservation(
                    List.of(
                            EnumSet.of(Signal.F, Signal.G, Signal.A, Signal.E, Signal.B,
                                    Signal.D),
                            EnumSet.of(Signal.C, Signal.G),
                            EnumSet.of(Signal.B, Signal.D, Signal.A, Signal.E, Signal.C),
                            EnumSet.of(Signal.G, Signal.D, Signal.A, Signal.F, Signal.B),
                            EnumSet.of(Signal.A, Signal.G, Signal.B, Signal.C, Signal.F,
                                    Signal.D),
                            EnumSet.of(Signal.G, Signal.D, Signal.C, Signal.B, Signal.E,
                                    Signal.F),
                            EnumSet.of(Signal.B, Signal.G, Signal.C, Signal.A, Signal.D),
                            EnumSet.of(Signal.G, Signal.F, Signal.A, Signal.C),
                            EnumSet.of(Signal.G, Signal.C, Signal.B),
                            EnumSet.of(Signal.C, Signal.D, Signal.G, Signal.A, Signal.B,
                                    Signal.E, Signal.F)),
                    List.of(
                            EnumSet.of(Signal.C, Signal.G),
                            EnumSet.of(Signal.C, Signal.G),
                            EnumSet.of(Signal.F, Signal.D, Signal.C, Signal.A, Signal.G,
                                    Signal.B),
                            EnumSet.of(Signal.C, Signal.B, Signal.G))),
            new DisplayObservation(
                    List.of(
                            EnumSet.of(Signal.F, Signal.B, Signal.E, Signal.G, Signal.C,
                                    Signal.D),
                            EnumSet.of(Signal.C, Signal.B, Signal.D),
                            EnumSet.of(Signal.A, Signal.D, Signal.C, Signal.E, Signal.F,
                                    Signal.B),
                            EnumSet.of(Signal.D, Signal.A, Signal.G, Signal.E, Signal.B),
                            EnumSet.of(Signal.A, Signal.F, Signal.C, Signal.B),
                            EnumSet.of(Signal.B, Signal.C),
                            EnumSet.of(Signal.A, Signal.E, Signal.F, Signal.D, Signal.C),
                            EnumSet.of(Signal.E, Signal.C, Signal.D, Signal.A, Signal.B),
                            EnumSet.of(Signal.F, Signal.G, Signal.D, Signal.E, Signal.C,
                                    Signal.A),
                            EnumSet.of(Signal.F, Signal.C, Signal.D, Signal.B, Signal.E,
                                    Signal.G, Signal.A)),
                    List.of(EnumSet.of(Signal.E, Signal.F, Signal.A,
                            Signal.B, Signal.C, Signal.D),
                            EnumSet.of(Signal.C, Signal.E, Signal.D, Signal.B, Signal.A),
                            EnumSet.of(Signal.G, Signal.A, Signal.D, Signal.F, Signal.E,
                                    Signal.C),
                            EnumSet.of(Signal.C, Signal.B))),
            new DisplayObservation(
                    List.of(
                            EnumSet.of(Signal.A, Signal.E, Signal.C, Signal.B, Signal.F,
                                    Signal.D, Signal.G),
                            EnumSet.of(Signal.F, Signal.B, Signal.G),
                            EnumSet.of(Signal.G, Signal.F),
                            EnumSet.of(Signal.B, Signal.A, Signal.F, Signal.E, Signal.G),
                            EnumSet.of(Signal.D, Signal.B, Signal.E, Signal.F, Signal.A),
                            EnumSet.of(Signal.F, Signal.C, Signal.G, Signal.E),
                            EnumSet.of(Signal.G, Signal.C, Signal.B, Signal.E, Signal.A),
                            EnumSet.of(Signal.F, Signal.C, Signal.A, Signal.E, Signal.G,
                                    Signal.B),
                            EnumSet.of(Signal.D, Signal.G, Signal.C, Signal.E, Signal.A,
                                    Signal.B),
                            EnumSet.of(Signal.F, Signal.C, Signal.B, Signal.D, Signal.G,
                                    Signal.A)),
                    List.of(
                            EnumSet.of(Signal.G, Signal.E, Signal.C, Signal.F),
                            EnumSet.of(Signal.E, Signal.G, Signal.D, Signal.C, Signal.A,
                                    Signal.B, Signal.F),
                            EnumSet.of(Signal.B, Signal.G, Signal.F),
                            EnumSet.of(Signal.B, Signal.F, Signal.G, Signal.E, Signal.A))),
            new DisplayObservation(
                    List.of(
                            EnumSet.of(Signal.F, Signal.G, Signal.E, Signal.A, Signal.B),
                            EnumSet.of(Signal.C, Signal.A),
                            EnumSet.of(Signal.A, Signal.F, Signal.C, Signal.E, Signal.B,
                                    Signal.G),
                            EnumSet.of(Signal.B, Signal.D, Signal.A, Signal.C, Signal.F,
                                    Signal.E, Signal.G),
                            EnumSet.of(Signal.C, Signal.F, Signal.A, Signal.E, Signal.D,
                                    Signal.G),
                            EnumSet.of(Signal.G, Signal.C, Signal.F, Signal.D, Signal.B),
                            EnumSet.of(Signal.B, Signal.A, Signal.E, Signal.C),
                            EnumSet.of(Signal.B, Signal.F, Signal.A, Signal.D, Signal.E,
                                    Signal.G),
                            EnumSet.of(Signal.B, Signal.A, Signal.F, Signal.G, Signal.C),
                            EnumSet.of(Signal.A, Signal.C, Signal.F)),
                    List.of(
                            EnumSet.of(Signal.G, Signal.E, Signal.B, Signal.D, Signal.C,
                                    Signal.F, Signal.A),
                            EnumSet.of(Signal.E, Signal.C, Signal.B, Signal.A),
                            EnumSet.of(Signal.C, Signal.A),
                            EnumSet.of(Signal.F, Signal.A, Signal.D, Signal.E, Signal.G,
                                    Signal.C, Signal.B))),
            new DisplayObservation(
                    List.of(
                            EnumSet.of(Signal.D, Signal.B, Signal.C, Signal.F, Signal.G),
                            EnumSet.of(Signal.F, Signal.G, Signal.D),
                            EnumSet.of(Signal.B, Signal.D, Signal.E, Signal.G, Signal.C,
                                    Signal.A, Signal.F),
                            EnumSet.of(Signal.F, Signal.G, Signal.E, Signal.C),
                            EnumSet.of(Signal.A, Signal.E, Signal.G, Signal.B, Signal.D,
                                    Signal.F),
                            EnumSet.of(Signal.E, Signal.C, Signal.D, Signal.F, Signal.A,
                                    Signal.B),
                            EnumSet.of(Signal.F, Signal.B, Signal.E, Signal.D, Signal.C),
                            EnumSet.of(Signal.D, Signal.A, Signal.C, Signal.G, Signal.B),
                            EnumSet.of(Signal.G, Signal.D, Signal.C, Signal.E, Signal.B,
                                    Signal.F),
                            EnumSet.of(Signal.G, Signal.F)),
                    List.of(
                            EnumSet.of(Signal.C, Signal.E, Signal.F, Signal.G),
                            EnumSet.of(Signal.D, Signal.C, Signal.B, Signal.E, Signal.F),
                            EnumSet.of(Signal.F, Signal.C, Signal.G, Signal.E),
                            EnumSet.of(Signal.G, Signal.B, Signal.C, Signal.A, Signal.D,
                                    Signal.F, Signal.E))),
            new DisplayObservation(
                    List.of(
                            EnumSet.of(Signal.B, Signal.D, Signal.F, Signal.E, Signal.G,
                                    Signal.C),
                            EnumSet.of(Signal.C, Signal.B, Signal.E, Signal.G, Signal.A,
                                    Signal.F),
                            EnumSet.of(Signal.G, Signal.E, Signal.C, Signal.B, Signal.F),
                            EnumSet.of(Signal.D, Signal.F, Signal.C, Signal.A, Signal.G,
                                    Signal.E),
                            EnumSet.of(Signal.B, Signal.D, Signal.A, Signal.C, Signal.G),
                            EnumSet.of(Signal.E, Signal.D),
                            EnumSet.of(Signal.B, Signal.E, Signal.D, Signal.F),
                            EnumSet.of(Signal.C, Signal.E, Signal.D),
                            EnumSet.of(Signal.A, Signal.D, Signal.C, Signal.B, Signal.E,
                                    Signal.F, Signal.G),
                            EnumSet.of(Signal.G, Signal.E, Signal.B, Signal.C, Signal.D)),
                    List.of(
                            EnumSet.of(Signal.E, Signal.D),
                            EnumSet.of(Signal.B, Signal.C, Signal.G, Signal.A, Signal.F,
                                    Signal.E),
                            EnumSet.of(Signal.C, Signal.D, Signal.G, Signal.B, Signal.A),
                            EnumSet.of(Signal.C, Signal.B, Signal.G, Signal.E, Signal.F))),
            new DisplayObservation(
                    List.of(
                            EnumSet.of(Signal.E, Signal.G, Signal.A, Signal.D, Signal.F,
                                    Signal.B),
                            EnumSet.of(Signal.C, Signal.D, Signal.B, Signal.F, Signal.E,
                                    Signal.G),
                            EnumSet.of(Signal.C, Signal.E, Signal.G, Signal.D),
                            EnumSet.of(Signal.F, Signal.E, Signal.C, Signal.A, Signal.B),
                            EnumSet.of(Signal.C, Signal.G, Signal.B),
                            EnumSet.of(Signal.G, Signal.B, Signal.D, Signal.E, Signal.F,
                                    Signal.C, Signal.A),
                            EnumSet.of(Signal.C, Signal.G),
                            EnumSet.of(Signal.F, Signal.G, Signal.C, Signal.D, Signal.A,
                                    Signal.B),
                            EnumSet.of(Signal.E, Signal.G, Signal.F, Signal.D, Signal.B),
                            EnumSet.of(Signal.B, Signal.F, Signal.C, Signal.E, Signal.G)),
                    List.of(
                            EnumSet.of(Signal.G, Signal.B, Signal.D, Signal.F, Signal.C,
                                    Signal.A, Signal.E),
                            EnumSet.of(Signal.B, Signal.G, Signal.C),
                            EnumSet.of(Signal.C, Signal.G),
                            EnumSet.of(Signal.C, Signal.G, Signal.B))),
            new DisplayObservation(
                    List.of(
                            EnumSet.of(Signal.G, Signal.C, Signal.A, Signal.F, Signal.B),
                            EnumSet.of(Signal.G, Signal.C, Signal.F),
                            EnumSet.of(Signal.D, Signal.C, Signal.A, Signal.E, Signal.B,
                                    Signal.F, Signal.G),
                            EnumSet.of(Signal.E, Signal.C, Signal.A, Signal.G, Signal.B),
                            EnumSet.of(Signal.G, Signal.F),
                            EnumSet.of(Signal.A, Signal.B, Signal.C, Signal.D, Signal.E,
                                    Signal.G),
                            EnumSet.of(Signal.G, Signal.A, Signal.E, Signal.F),
                            EnumSet.of(Signal.C, Signal.A, Signal.F, Signal.B, Signal.G,
                                    Signal.E),
                            EnumSet.of(Signal.F, Signal.D, Signal.B, Signal.A, Signal.C),
                            EnumSet.of(Signal.F, Signal.E, Signal.G, Signal.B, Signal.D,
                                    Signal.C)),
                    List.of(
                            EnumSet.of(Signal.F, Signal.G, Signal.A, Signal.E),
                            EnumSet.of(Signal.C, Signal.F, Signal.G, Signal.A, Signal.B),
                            EnumSet.of(Signal.F, Signal.G),
                            EnumSet.of(Signal.B, Signal.A, Signal.G, Signal.C, Signal.E))));

    @Test
    public void example1() {
        assertThat(Day08Parser.INPUT
                .parse("""
                        be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
                        edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
                        fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
                        fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
                        aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
                        fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
                        dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
                        bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
                        egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
                        gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
                        """))
                        .isEqualTo(EXAMPLE_INPUT);
    }
}
