package com.example.denaun.aoc2021.day19;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.jparsec.Parser;


class Day19 {
    private Day19() {}

    private static final Parser<List<Set<Coordinate3d>>> PARSER = Day19Parser.INPUT;

    static int part1(String input) {
        var scanners = PARSER.parse(input);
        var trench = matchBeacons(scanners);
        return trench.beacons().size();
    }

    static int part2(String input) {
        var scanners = PARSER.parse(input);
        var trench = matchBeacons(scanners);
        return oceanSize(trench.scanners());
    }

    static Measurement matchBeacons(List<Set<Coordinate3d>> scanners) {
        checkArgument(!scanners.isEmpty());
        var unmatched = scanners.stream()
                .map(scanner -> new Measurement(List.of(Coordinate3d.origin()), scanner))
                .collect(Collectors.toCollection(LinkedList::new));
        while (!unmatched.isEmpty()) {
            var first = unmatched.removeFirst();
            if (unmatched.isEmpty()) {
                return first;
            }
            var merged = Optional.<Measurement>empty();
            for (var second : unmatched) {
                var matched = tryMatch(first, second);
                if (matched.isPresent()) {
                    var arranged = matched.get().apply(second);
                    first = new Measurement(
                            ImmutableList.<Coordinate3d>builder()
                                    .addAll(first.scanners())
                                    .addAll(arranged.scanners())
                                    .build(),
                            ImmutableSet.<Coordinate3d>builder()
                                    .addAll(first.beacons())
                                    .addAll(arranged.beacons())
                                    .build());
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
    private static final List<UnaryOperator<Coordinate3d>> ROTATIONS = Stream
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
            .<UnaryOperator<Coordinate3d>>map(f -> f::apply)
            .toList();

    private static Optional<Arrangement> tryMatch(Measurement first,
            Measurement second) {
        return ROTATIONS.parallelStream()
                .flatMap(rotation -> allShifts(
                        second.beacons().stream().map(rotation).toList(),
                        first.beacons())
                                .map(shift -> new Arrangement(shift, rotation)))
                .filter(arrangement -> haveOverlap(
                        first.beacons(),
                        second.beacons().stream().map(arrangement::apply)))
                .findAny();
    }

    private static Stream<Coordinate3d> allShifts(
            Collection<Coordinate3d> toShift,
            Collection<Coordinate3d> reference) {
        return reference.stream()
                .flatMap(ref -> toShift.stream().map(ref::difference));
    }

    private static boolean haveOverlap(Set<Coordinate3d> first, Stream<Coordinate3d> second) {
        return second.filter(first::contains).count() >= 12;
    }

    static int oceanSize(List<Coordinate3d> beacons) {
        return IntStream.range(0, beacons.size())
                .flatMap(i -> IntStream.range(i + 1, beacons.size())
                        .map(j -> beacons.get(i).distance(beacons.get(j))))
                .max().orElseThrow();
    }
}
