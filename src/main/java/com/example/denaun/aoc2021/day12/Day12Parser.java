package com.example.denaun.aoc2021.day12;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocScanners.LOWER_CASE;
import static com.example.denaun.aoc2021.parsers.AocScanners.UPPER_CASE;
import static org.jparsec.Scanners.isChar;
import static org.jparsec.Scanners.string;

import com.example.denaun.aoc2021.parsers.AocParsers;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day12Parser {
    private Day12Parser() {}

    static final Parser<Terminal> TERMINAL = Parsers.or(
            string("start").retn(Terminal.START),
            string("end").retn(Terminal.END));
    static final Parser<SmallCave> SMALL_CAVE = LOWER_CASE.map(SmallCave::new);
    static final Parser<LargeCave> LARGE_CAVE = UPPER_CASE.map(LargeCave::new);
    static final Parser<Node> NODE = Parsers.or(TERMINAL, SMALL_CAVE, LARGE_CAVE);

    static final Parser<EndpointPair<Node>> EDGE =
            AocParsers.sepPair(isChar('-'), NODE, EndpointPair::unordered);
    static final Parser<Graph<Node>> GRAPH = EDGE.endBy1(LINE_ENDING)
            .map(edges -> {
                var graph = GraphBuilder.undirected()
                        .<Node>immutable();
                for (var edge : edges) {
                    graph.putEdge(edge);
                }
                return graph.build();
            });
}
