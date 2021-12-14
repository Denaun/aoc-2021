package com.example.denaun.aoc2021.day14;

import java.util.HashMap;
import java.util.Map;

record Element(String name) {
    private static final Map<String, Element> ELEMENTS = new HashMap<>();

    static Element of(String name) {
        return ELEMENTS.computeIfAbsent(name, Element::new);
    }
}
