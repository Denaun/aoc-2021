package com.example.denaun.aoc2021.day03;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Verify.verify;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

record DiagnosticReport(int numBits, List<Integer> numbers) {
    int mostCommonBits() {
        return IntStream.range(0, numBits)
                .reduce(0, (result, bit) -> {
                    if (mostCommonValue(bit, numbers)) {
                        return result | (1 << bit);
                    } else {
                        return result;
                    }
                });
    }

    static boolean mostCommonValue(int bit, Collection<Integer> numbers) {
        var bitOffset = 1 << bit;
        var numOnes = numbers.stream()
                .filter(number -> (number & bitOffset) != 0)
                .count();
        return numOnes >= (numbers.size() + 1) / 2;
    }

    static boolean leastCommonValue(int bit, Collection<Integer> numbers) {
        return !mostCommonValue(bit, numbers);
    }

    int filterBits(BiPredicate<Integer, Collection<Integer>> criteria) {
        checkState(!numbers.isEmpty());
        var candidates = new HashSet<>(numbers);
        for (var bit = numBits - 1; bit >= 0; --bit) {
            if (candidates.size() == 1) {
                break;
            }
            var keepIfSet = criteria.test(bit, candidates);
            var mask = (1 << bit);
            candidates.removeIf(number -> ((number & mask) != 0) != keepIfSet);
        }
        verify(candidates.size() == 1);
        return candidates.iterator().next();
    }
}
