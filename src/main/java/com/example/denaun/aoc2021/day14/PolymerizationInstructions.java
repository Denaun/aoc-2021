package com.example.denaun.aoc2021.day14;

import com.google.common.collect.Streams;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record PolymerizationInstructions(
        List<Element> polymer_template,
        Map<Map.Entry<Element, Element>, Element> pair_insertion_rules) {
    Map<Element, Long> estimateProcess(int times) {
        var pairCounts = Streams
                .zip(polymer_template.stream(), polymer_template.stream().skip(1), Map::entry)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (var i = 0; i < times; ++i) {
            pairCounts = pairCounts.entrySet().stream()
                    .flatMap(entry -> {
                        var pair = entry.getKey();
                        var count = entry.getValue();
                        var a = pair.getKey();
                        var b = pair.getValue();
                        var c = Optional.ofNullable(pair_insertion_rules.get(pair));
                        if (c.isEmpty()) {
                            return Stream.of(entry);
                        }
                        return Stream.of(
                                Map.entry(Map.entry(a, c.get()), count),
                                Map.entry(Map.entry(c.get(), b), count));
                    })
                    .collect(Collectors.groupingBy(Map.Entry::getKey,
                            Collectors.summingLong(Map.Entry::getValue)));
        }
        // Only count the second element of each pair and adjust by adding the first element of the
        // polymer. (Alternatively could sum the first element and add the last.)
        var counts = pairCounts.entrySet().stream()
                .collect(Collectors.groupingBy(
                        entry -> entry.getKey().getValue(),
                        Collectors.summingLong(Map.Entry::getValue)));
        if (!polymer_template.isEmpty()) {
            counts.merge(polymer_template.get(0), 1L, Long::sum);
        }
        return counts;
    }
}
