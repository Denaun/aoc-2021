package com.example.denaun.aoc2021.day12;

import com.google.common.graph.Graph;
import com.google.common.graph.Graphs;
import java.util.HashSet;
import java.util.Set;
import org.jparsec.Parser;

class Day12 {
    private Day12() {}

    static final Parser<Graph<Node>> PARSER = Day12Parser.GRAPH;

    static int part1(String input) {
        var graph = PARSER.parse(input);
        return countPaths(new VisitSmallCavesOnce(graph));
    }

    static int part2(String input) {
        var graph = PARSER.parse(input);
        return countPaths(new VisitOneSmallCaveTwice(graph));
    }

    static int countPaths(VisitStrategy strategy) {
        return countPaths(strategy, Terminal.START);
    }

    private static int countPaths(VisitStrategy strategy, Node node) {
        if (Terminal.END.equals(node)) {
            return 1;
        }
        var next = strategy.visiting(node);
        return strategy.graph().adjacentNodes(node).stream()
                .filter(next.graph().nodes()::contains)
                .mapToInt(neighbor -> countPaths(next, neighbor))
                .sum();
    }

    private interface VisitStrategy {
        Graph<Node> graph();

        VisitStrategy visiting(Node node);
    }

    static record VisitSmallCavesOnce(Graph<Node> graph) implements VisitStrategy {
        @Override
        public VisitStrategy visiting(Node node) {
            if (node instanceof LargeCave) {
                return this;
            }
            var subgraph = Graphs.copyOf(graph);
            subgraph.removeNode(node);
            return new VisitSmallCavesOnce(subgraph);
        }
    }

    static class VisitOneSmallCaveTwice implements VisitStrategy {
        private final VisitStrategy backingVisitor;
        private final Set<SmallCave> visited;

        VisitOneSmallCaveTwice(Graph<Node> graph) {
            this(new VisitSmallCavesOnce(graph), Set.of());
        }

        private VisitOneSmallCaveTwice(VisitStrategy backingVisitor, Set<SmallCave> visited) {
            this.backingVisitor = backingVisitor;
            this.visited = visited;
        }

        @Override
        public Graph<Node> graph() {
            return backingVisitor.graph();
        }

        @Override
        public VisitStrategy visiting(Node node) {
            if (node instanceof SmallCave cave) {
                if (visited.contains(cave)) {
                    var subgraph = Graphs.copyOf(graph());
                    visited.stream().forEach(subgraph::removeNode);
                    return new VisitSmallCavesOnce(subgraph);
                }
                var newVisited = new HashSet<>(visited);
                newVisited.add(cave);
                return new VisitOneSmallCaveTwice(backingVisitor, newVisited);
            }
            return new VisitOneSmallCaveTwice(backingVisitor.visiting(node), visited);
        }
    }
}
