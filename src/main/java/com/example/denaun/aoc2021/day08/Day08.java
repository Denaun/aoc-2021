package com.example.denaun.aoc2021.day08;

import com.google.common.base.Predicates;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jparsec.Parser;

class Day08 {
    private Day08() {}

    private static final Parser<List<DisplayObservation>> PARSER = Day08Parser.INPUT;
    private static BiMap<Integer, EnumSet<Segment>> DISPLAY_MAPPING = ImmutableBiMap.of(
            0, EnumSet.of(Segment.TOP, Segment.TOP_LEFT, Segment.TOP_RIGHT, Segment.BOTTOM_LEFT,
                    Segment.BOTTOM_RIGHT, Segment.BOTTOM),
            1, EnumSet.of(Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT),
            2, EnumSet.of(Segment.TOP, Segment.TOP_RIGHT, Segment.MIDDLE, Segment.BOTTOM_LEFT,
                    Segment.BOTTOM),
            3, EnumSet.of(Segment.TOP, Segment.TOP_RIGHT, Segment.MIDDLE, Segment.BOTTOM_RIGHT,
                    Segment.BOTTOM),
            4, EnumSet.of(Segment.TOP_LEFT, Segment.TOP_RIGHT, Segment.MIDDLE,
                    Segment.BOTTOM_RIGHT),
            5, EnumSet.of(Segment.TOP, Segment.TOP_LEFT, Segment.MIDDLE, Segment.BOTTOM_RIGHT,
                    Segment.BOTTOM),
            6, EnumSet.of(Segment.TOP, Segment.TOP_LEFT, Segment.MIDDLE, Segment.BOTTOM_LEFT,
                    Segment.BOTTOM_RIGHT, Segment.BOTTOM),
            7, EnumSet.of(Segment.TOP, Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT),
            8, EnumSet.allOf(Segment.class),
            9, EnumSet.of(Segment.TOP, Segment.TOP_LEFT, Segment.TOP_RIGHT, Segment.MIDDLE,
                    Segment.BOTTOM_RIGHT, Segment.BOTTOM));

    static long part1(String input) {
        var observations = PARSER.parse(input);
        return observations.stream()
                .flatMap(i -> i.output().stream())
                .filter(o -> tryRecognizeDigit(o).isPresent())
                .count();
    }

    static int part2(String input) {
        var observations = PARSER.parse(input);
        return observations.stream()
                .mapToInt(observation -> {
                    var decoded = determineEncoding(observation.training());
                    return recognizeDisplay(observation.output(), decoded);
                })
                .sum();
    }

    static Optional<Integer> tryRecognizeDigit(Set<Signal> signals) {
        return tryRecognizeDigit(signals, Map.of());
    }

    static Optional<Integer> tryRecognizeDigit(Set<Signal> signals, Map<Signal, Segment> encoding) {
        return switch (signals.size()) {
            case 2 -> Optional.of(1);
            case 4 -> Optional.of(4);
            case 3 -> Optional.of(7);
            case 7 -> Optional.of(8);
            default -> {
                if (!signals.stream().allMatch(encoding::containsKey)) {
                    yield Optional.empty();
                }
                var segments = signals.stream()
                        .map(encoding::get)
                        .collect(Collectors.toCollection(() -> EnumSet.noneOf(Segment.class)));
                if (!DISPLAY_MAPPING.inverse().containsKey(segments)) {
                    yield Optional.empty();
                }
                yield Optional.of(DISPLAY_MAPPING.inverse().get(segments));
            }
        };
    }

    static int recognizeDisplay(List<EnumSet<Signal>> output, Map<Signal, Segment> encoding) {
        return output.stream()
                .mapToInt(signals -> Day08.tryRecognizeDigit(signals, encoding).get())
                .reduce(0, (result, digit) -> result * 10 + digit);
    }

    static Map<Signal, Segment> determineEncoding(Collection<EnumSet<Signal>> training) {
        return tryDetermineEncoding(training, new EnumMap<>(Signal.class)).orElseThrow();
    }

    static Optional<Map<Signal, Segment>> tryDetermineEncoding(
            Collection<EnumSet<Signal>> training,
            EnumMap<Signal, Segment> partialEncoding) {
        if (partialEncoding.size() == Segment.values().length) {
            if (training.stream()
                    .anyMatch(signals -> tryRecognizeDigit(signals, partialEncoding).isEmpty())) {
                return Optional.empty();
            }
            return Optional.of(partialEncoding);
        }
        // NOTE: Taking the first possibility means we might take longer to fail.
        var segment = Stream.of(Segment.values())
                .filter(Predicates.not(partialEncoding::containsValue))
                .findFirst()
                .orElseThrow();
        var signals = signalsForSegment(segment, training, partialEncoding);
        for (var signal : signals) {
            var tentative = new EnumMap<>(partialEncoding);
            tentative.put(signal, segment);
            var encoding = tryDetermineEncoding(training, tentative);
            if (encoding.isPresent()) {
                return encoding;
            }
        }
        return Optional.empty();
    }

    static EnumSet<Signal> signalsForSegment(
            Segment segment,
            Collection<EnumSet<Signal>> training,
            Map<Signal, Segment> partialEncoding) {
        var valid = EnumSet.allOf(Signal.class);
        valid.removeAll(partialEncoding.keySet());
        for (var signals : training) {
            var digit = tryRecognizeDigit(signals, partialEncoding);
            if (digit.isPresent() && DISPLAY_MAPPING.get(digit.get()).contains(segment)) {
                valid.retainAll(signals);
            }
        }
        return valid;
    }
}
