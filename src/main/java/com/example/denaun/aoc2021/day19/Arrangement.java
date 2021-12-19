package com.example.denaun.aoc2021.day19;

import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

record Arrangement(Coordinate3d shift, UnaryOperator<Coordinate3d> rotation) {
    Coordinate3d apply(Coordinate3d arg) {
        return shift.sum(rotation.apply(arg));
    }

    Measurement apply(Measurement arg) {
        return new Measurement(
                arg.scanners().stream()
                        .map(this::apply)
                        .toList(),
                arg.beacons().stream()
                        .map(this::apply)
                        .collect(Collectors.toUnmodifiableSet()));
    }
}
