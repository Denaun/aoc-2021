package com.example.denaun.aoc2021.day19;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toUnmodifiableSet;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;
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
        List<Measurement> unmatched = scanners.stream()
                .sorted(comparingInt(Set<Coordinate3d>::size).reversed())
                .map(beacons -> new Measurement(List.of(Coordinate3d.origin()), beacons))
                .collect(toCollection(LinkedList::new));
        var reference = unmatched.remove(0);
        // Assuming that the largest group can always be expanded, we need up to N repetitions to
        // merge everything.
        for (var i = 0; i < scanners.size(); ++i) {
            final var referenceBeacons = reference.beacons();
            Map<Optional<Measurement>, List<Measurement>> partitioned = unmatched.stream()
                    .collect(groupingBy(
                            other -> tryMatch(referenceBeacons, other.beacons())
                                    .map(arrangement -> arrangement.apply(other))));
            unmatched = Optional
                    .ofNullable(partitioned.remove(Optional.empty()))
                    .orElse(List.of());
            var matched = partitioned.keySet().stream()
                    .map(Optional::get)
                    .toList();

            reference = new Measurement(
                    Stream.concat(Stream.of(reference), matched.stream())
                            .map(Measurement::scanners)
                            .flatMap(List::stream)
                            .toList(),
                    Stream.concat(Stream.of(reference), matched.stream())
                            .map(Measurement::beacons)
                            .flatMap(Set::stream)
                            .collect(toUnmodifiableSet()));
        }
        checkArgument(unmatched.isEmpty());
        return reference;
    }

    private static final List<UnaryOperator<Coordinate3d>> ROTATIONS = Stream
            // Facing X, Y, or Z.
            .<UnaryOperator<Coordinate3d>>of(
                    c -> c,
                    c -> new Coordinate3d(c.y(), c.z(), c.x()),
                    c -> new Coordinate3d(c.z(), c.x(), c.y()))
            .flatMap(op -> Stream
                    // Facing positive or negative.
                    .<UnaryOperator<Coordinate3d>>of(
                            c -> c,
                            c -> new Coordinate3d(-c.x(), c.y(), -c.z()))
                    .map(op::andThen))
            .flatMap(op -> Stream
                    // Considering any other direction as UP.
                    .<UnaryOperator<Coordinate3d>>of(
                            c -> c,
                            c -> new Coordinate3d(c.x(), c.z(), -c.y()),
                            c -> new Coordinate3d(c.x(), -c.y(), -c.z()),
                            c -> new Coordinate3d(c.x(), -c.z(), c.y()))
                    .map(op::andThen))
            .<UnaryOperator<Coordinate3d>>map(f -> f::apply)
            .toList();

    private static Optional<Arrangement> tryMatch(
            Set<Coordinate3d> reference,
            Collection<Coordinate3d> other) {
        return ROTATIONS.parallelStream()
                .flatMap(rotation -> allShifts(other.stream().map(rotation).toList(), reference)
                        .map(shift -> new Arrangement(shift, rotation)))
                .filter(arrangement -> haveOverlap(
                        reference, other.stream().map(arrangement::apply)))
                .findAny();
    }

    private static Stream<Coordinate3d> allShifts(
            Collection<Coordinate3d> toShift,
            Collection<Coordinate3d> reference) {
        return reference.stream()
                .flatMap(ref -> toShift.stream().map(ref::difference));
    }

    private static boolean haveOverlap(
            Set<Coordinate3d> reference,
            Stream<Coordinate3d> other) {
        return other.filter(reference::contains).count() >= 12;
    }

    static int oceanSize(List<Coordinate3d> beacons) {
        return IntStream.range(0, beacons.size())
                .flatMap(i -> IntStream.range(i + 1, beacons.size())
                        .map(j -> beacons.get(i).distance(beacons.get(j))))
                .max().orElseThrow();
    }
}
