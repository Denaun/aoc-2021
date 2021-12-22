package com.example.denaun.aoc2021.day22;

import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.RangeMap;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeMap;
import com.google.common.collect.TreeRangeSet;
import java.util.stream.Stream;

class CuboidSet<C extends Comparable<C>> {
    private CuboidSet() {}

    private TreeRangeMap<C, RangeMap<C, RangeSet<C>>> cuboids = TreeRangeMap.create();

    static <C extends Comparable<C>> CuboidSet<C> create() {
        return new CuboidSet<>();
    }

    Stream<Cuboid<C>> stream() {
        return cuboids.asMapOfRanges().entrySet().stream()
                .flatMap(xEntry -> xEntry.getValue().asMapOfRanges().entrySet().stream()
                        .flatMap(yEntry -> yEntry.getValue().asRanges().stream()
                                .map(zRange -> new Cuboid<>(
                                        xEntry.getKey(),
                                        yEntry.getKey(),
                                        zRange))));
    }

    void add(Cuboid<C> cuboid) {
        cuboids.merge(
                cuboid.x(),
                ImmutableRangeMap.<C, RangeSet<C>>builder()
                        .put(cuboid.y(), ImmutableRangeSet.of(cuboid.z()))
                        .build(),
                CuboidSet::mergeTrees);
    }

    private static <C extends Comparable<C>> RangeMap<C, RangeSet<C>> mergeTrees(
            RangeMap<C, RangeSet<C>> left,
            RangeMap<C, RangeSet<C>> right) {
        var result = TreeRangeMap.<C, RangeSet<C>>create();
        result.putAll(left);
        for (var entry : right.asMapOfRanges().entrySet()) {
            result.merge(entry.getKey(), entry.getValue(), CuboidSet::mergeTrees);
        }
        return result;
    }

    private static <C extends Comparable<C>> RangeSet<C> mergeTrees(
            RangeSet<C> left,
            RangeSet<C> right) {
        var result = TreeRangeSet.create(left);
        result.addAll(right);
        return result;
    }

    void remove(Cuboid<C> cuboid) {
        var x = TreeRangeMap.<C, RangeMap<C, RangeSet<C>>>create();
        x.putAll(cuboids);
        for (var yEntry : cuboids
                .subRangeMap(cuboid.x())
                .asMapOfRanges().entrySet()) {
            var y = TreeRangeMap.<C, RangeSet<C>>create();
            y.putAll(yEntry.getValue());
            for (var xEntry : yEntry.getValue()
                    .subRangeMap(cuboid.y())
                    .asMapOfRanges().entrySet()) {
                var z = TreeRangeSet.create(xEntry.getValue());
                z.remove(cuboid.z());
                y.putCoalescing(xEntry.getKey(), z);
            }
            x.putCoalescing(yEntry.getKey(), y);
        }
        cuboids = x;
    }
}
