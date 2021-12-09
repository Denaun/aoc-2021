package com.example.denaun.aoc2021.day08;

import static com.google.common.truth.Truth.assertThat;

import com.example.denaun.aoc2021.AocTestCase;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Day08Test extends AocTestCase {
    public Day08Test() throws IOException {
        super("day08.in");
    }

    @Test
    public void example1() {
        assertThat(Day08ParserTest.EXAMPLE_INPUT.stream()
                .flatMap(i -> i.output().stream())
                .filter(o -> Day08.tryRecognizeDigit(o).isPresent())
                .count())
                        .isEqualTo(26);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day08.part1(input)).isEqualTo(344);
    }

    @Test
    public void example2() {
        var training = List.of(
                EnumSet.of(Signal.A, Signal.C, Signal.E, Signal.D, Signal.G, Signal.F, Signal.B),
                EnumSet.of(Signal.C, Signal.D, Signal.F, Signal.B, Signal.E),
                EnumSet.of(Signal.G, Signal.C, Signal.D, Signal.F, Signal.A),
                EnumSet.of(Signal.F, Signal.B, Signal.C, Signal.A, Signal.D),
                EnumSet.of(Signal.D, Signal.A, Signal.B),
                EnumSet.of(Signal.C, Signal.E, Signal.F, Signal.A, Signal.B, Signal.D),
                EnumSet.of(Signal.C, Signal.D, Signal.F, Signal.G, Signal.E, Signal.B),
                EnumSet.of(Signal.E, Signal.A, Signal.F, Signal.B),
                EnumSet.of(Signal.C, Signal.A, Signal.G, Signal.E, Signal.D, Signal.B),
                EnumSet.of(Signal.A, Signal.B));
        var encoding = Day08.determineEncoding(training);
        assertThat(encoding)
                .containsExactlyEntriesIn(Map.of(
                        Signal.D, Segment.TOP,
                        Signal.E, Segment.TOP_LEFT,
                        Signal.A, Segment.TOP_RIGHT,
                        Signal.F, Segment.MIDDLE,
                        Signal.G, Segment.BOTTOM_LEFT,
                        Signal.B, Segment.BOTTOM_RIGHT,
                        Signal.C, Segment.BOTTOM));
        assertThat(training.stream()
                .map(signals -> Day08.tryRecognizeDigit(signals, encoding).orElseThrow()).toList())
                        .containsExactly(8, 5, 2, 3, 7, 9, 6, 4, 0, 1)
                        .inOrder();
        var output = List.of(
                EnumSet.of(Signal.C, Signal.D, Signal.F, Signal.E, Signal.B),
                EnumSet.of(Signal.F, Signal.C, Signal.A, Signal.D, Signal.B),
                EnumSet.of(Signal.C, Signal.D, Signal.F, Signal.E, Signal.B),
                EnumSet.of(Signal.C, Signal.D, Signal.B, Signal.A, Signal.F));
        assertThat(Day08.recognizeDisplay(output, encoding))
                .isEqualTo(5353);
    }

    @Test
    public void example3() {
        assertThat(Day08ParserTest.EXAMPLE_INPUT.stream()
                .mapToLong(observation -> {
                    var decoded = Day08.determineEncoding(observation.training());
                    return Day08.recognizeDisplay(observation.output(), decoded);
                })
                .sum())
                        .isEqualTo(61_229);
    }

    @Test
    @Override
    public void part2() {
        assertThat(Day08.part2(input)).isEqualTo(1_048_410);
    }
}
