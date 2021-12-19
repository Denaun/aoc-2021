package com.example.denaun.aoc2021.day19;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import org.jparsec.Parser;


class Day19 {
    private Day19() {}

    private static final Parser<List<Set<Coordinate3d>>> PARSER = Day19Parser.INPUT;

    static int part1(String input) {
        var scanners = PARSER.parse(input);
        var beacons = matchBeacons(scanners);
        return beacons.size();
    }

    static Set<Coordinate3d> matchBeacons(List<Set<Coordinate3d>> scanners) {
        checkArgument(!scanners.isEmpty());
        var unmatched = new LinkedList<>(scanners);
        while (!unmatched.isEmpty()) {
            var first = unmatched.removeFirst();
            if (unmatched.isEmpty()) {
                return first;
            }
            var merged = Optional.<Set<Coordinate3d>>empty();
            for (var second : unmatched) {
                var matched = tryMatch(first, second);
                if (matched.isPresent()) {
                    first = matched.get();
                    merged = Optional.of(second);
                    break;
                }
            }
            unmatched.addLast(first);
            merged.ifPresent(unmatched::remove);
        }
        throw new IllegalStateException();
    }

    // TODO: This should be smaller (24 elements).
    private static final List<Function<Coordinate3d, Coordinate3d>> ROTATIONS = Stream
            .<UnaryOperator<Coordinate3d>>of(
                    c -> c,
                    c -> new Coordinate3d(c.x(), c.y(), -c.z()),
                    c -> new Coordinate3d(c.x(), -c.y(), c.z()),
                    c -> new Coordinate3d(c.x(), -c.y(), -c.z()),
                    c -> new Coordinate3d(-c.x(), c.y(), c.z()),
                    c -> new Coordinate3d(-c.x(), c.y(), -c.z()),
                    c -> new Coordinate3d(-c.x(), -c.y(), c.z()),
                    c -> new Coordinate3d(-c.x(), -c.y(), -c.z()))
            .flatMap(op -> Stream
                    .<UnaryOperator<Coordinate3d>>of(
                            c -> c,
                            c -> new Coordinate3d(c.x(), c.z(), c.y()),
                            c -> new Coordinate3d(c.y(), c.x(), c.z()),
                            c -> new Coordinate3d(c.y(), c.z(), c.x()),
                            c -> new Coordinate3d(c.z(), c.x(), c.y()),
                            c -> new Coordinate3d(c.z(), c.y(), c.x()))
                    .map(op::andThen))
            .toList();

    private static Optional<Set<Coordinate3d>> tryMatch(Set<Coordinate3d> first,
            Set<Coordinate3d> second) {
        return allRotations(second)
                .parallel()
                .flatMap(rotated -> allShifts(rotated, first))
                .filter(shifted -> haveOverlap(first, shifted))
                .findAny()
                .map(shifted -> ImmutableSet.<Coordinate3d>builder()
                        .addAll(first)
                        .addAll(shifted)
                        .build());
    }

    private static Stream<Collection<Coordinate3d>> allRotations(
            Collection<Coordinate3d> toRotate) {
        return ROTATIONS.stream()
                .map(rotation -> toRotate.stream().map(rotation).toList());
    }

    private static Stream<Collection<Coordinate3d>> allShifts(
            Collection<Coordinate3d> toShift,
            Collection<Coordinate3d> reference) {
        return reference.stream()
                .flatMap(ref -> toShift.stream().map(ref::difference))
                .map(shift -> toShift.stream().map(shift::sum).toList());
    }

    private static boolean haveOverlap(Set<Coordinate3d> first, Collection<Coordinate3d> second) {
        return second.stream().filter(first::contains).count() >= 12;
    }
}
