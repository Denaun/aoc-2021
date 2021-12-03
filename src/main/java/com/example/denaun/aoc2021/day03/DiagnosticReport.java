package com.example.denaun.aoc2021.day03;

import java.util.List;
import java.util.stream.IntStream;

record DiagnosticReport(int numBits, List<Integer> numbers) {
    int mostCommonBits() {
        return IntStream.range(0, numBits)
                .reduce(0, (result, bit) -> {
                    var bitOffset = 1 << bit;
                    var numOnes = numbers.stream()
                            .filter(number -> (number & bitOffset) != 0)
                            .count();
                    if (numOnes >= numbers.size() / 2) {
                        return result | bitOffset;
                    } else {
                        return result;
                    }
                });
    }
}
