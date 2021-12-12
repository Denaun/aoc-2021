package com.example.denaun.aoc2021.day12;

import com.google.common.graph.Graph;
import com.google.common.graph.Graphs;
import org.jparsec.Parser;

class Day12 {
    private Day12() {}

    static final Parser<Graph<Node>> PARSER = Day12Parser.GRAPH;

    static int part1(String input) {
        var graph = PARSER.parse(input);
        return countPaths(graph);
    }

    static int countPaths(Graph<Node> graph) {
        return countPaths(graph, Terminal.START);
    }

    private static int countPaths(Graph<Node> graph, Node node) {
        if (Terminal.END.equals(node)) {
            return 1;
        }
        Graph<Node> next;
        if (node instanceof LargeCave) {
            next = graph;
        } else {
            var subgraph = Graphs.copyOf(graph);
            subgraph.removeNode(node);
            next = subgraph;
        }
        return graph.adjacentNodes(node).stream()
                .mapToInt(neighbor -> countPaths(next, neighbor))
                .sum();
    }
}
