package com.example.denaun.aoc2021.day14;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocParsers.sepPair;
import static org.jparsec.Scanners.string;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.pattern.CharPredicates;
import org.jparsec.pattern.Patterns;

class Day14Parser {
    private Day14Parser() {}

    private static final Parser<Element> ELEMENT = Patterns.isChar(CharPredicates.IS_UPPER_CASE)
            .toScanner("element").source().map(Element::of);

    private static final Parser<List<Element>> POLYMER_TEMPLATE =
            ELEMENT.many1().followedBy(LINE_ENDING);

    private static final Parser<Map.Entry<Element, Map.Entry<Element, Element>>> PAIR_INSERTION_RULE =
            Parsers.sequence(ELEMENT, ELEMENT.followedBy(string(" -> ")), ELEMENT,
                    (a, b, c) -> Map.entry(a, Map.entry(b, c)));
    private static final Parser<Map<Element, Map<Element, Element>>> PAIR_INSERTION_RULES =
            PAIR_INSERTION_RULE.endBy1(LINE_ENDING)
                    .map(rules -> rules.stream()
                            .collect(Collectors.groupingBy(Map.Entry::getKey,
                                    Collectors.mapping(Map.Entry::getValue,
                                            Collectors.toMap(
                                                    Map.Entry::getKey,
                                                    Map.Entry::getValue)))));

    static final Parser<PolymerizationInstructions> INPUT =
            sepPair(POLYMER_TEMPLATE, LINE_ENDING, PAIR_INSERTION_RULES,
                    PolymerizationInstructions::new);
}
