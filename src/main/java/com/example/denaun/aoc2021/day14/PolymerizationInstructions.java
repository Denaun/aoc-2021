package com.example.denaun.aoc2021.day14;

import com.google.common.collect.Streams;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

record PolymerizationInstructions(
        List<Element> polymer_template,
        Map<Element, Map<Element, Element>> pair_insertion_rules) {
    List<Element> process(int times) {
        if (polymer_template.isEmpty()) {
            return polymer_template;
        }
        if (polymer_template.size() == 1) {
            return polymer_template;
        }
        var polymer = polymer_template;
        for (var i = 0; i < times; ++i) {
            polymer = process(polymer);
        }
        return polymer;
    }

    private List<Element> process(List<Element> polymer) {
        var result = new ArrayList<Element>();
        Streams.forEachPair(polymer.stream(), polymer.stream().skip(1),
                (a, b) -> {
                    result.add(a);
                    var insertion = Optional.ofNullable(
                            pair_insertion_rules
                                    .getOrDefault(a, Map.of())
                                    .get(b));
                    if (insertion.isPresent()) {
                        result.add(insertion.get());
                    }
                });
        result.add(polymer.get(polymer.size() - 1));
        return result;
    }
}
