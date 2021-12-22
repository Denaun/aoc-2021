package com.example.denaun.aoc2021.day22;

import com.google.common.collect.Range;
import java.util.Optional;

record Cuboid<C extends Comparable<C>> (Range<C> x, Range<C> y, Range<C> z) {
    static final <C extends Comparable<C>> Cuboid<C> closed(
            C lowerX, C lowerY, C lowerZ,
            C upperX, C upperY, C upperZ) {
        return new Cuboid<>(
                Range.closed(lowerX, upperX),
                Range.closed(lowerY, upperY),
                Range.closed(lowerZ, upperZ));
    }

    static final <C extends Comparable<C>> Cuboid<C> allClosed(C lower, C upper) {
        return Cuboid.closed(
                lower, lower, lower,
                upper, upper, upper);
    }

    Optional<Cuboid<C>> intersect(Cuboid<C> other) {
        if (encloses(other)) {
            return Optional.of(other);
        }
        if (other.encloses(this)) {
            return Optional.of(this);
        }
        if (!x.isConnected(other.x)
                || !y.isConnected(other.y)
                || !z.isConnected(other.z)) {
            return Optional.empty();
        }
        return Optional.of(new Cuboid<>(
                x.intersection(other.x),
                y.intersection(other.y),
                z.intersection(other.z)));
    }

    boolean encloses(Cuboid<C> right) {
        return x.encloses(right.x)
                && y.encloses(right.y)
                && z.encloses(right.z);
    }
}
