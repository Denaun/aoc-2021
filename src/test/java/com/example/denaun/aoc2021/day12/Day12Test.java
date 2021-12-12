package com.example.denaun.aoc2021.day12;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.denaun.aoc2021.AocTestCase;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import java.io.IOException;
import org.junit.Test;

public class Day12Test extends AocTestCase {
    public Day12Test() throws IOException {
        super("day12.in");
    }

    @Test
    public void example1() {
        MutableGraph<Node> graph = GraphBuilder.undirected().build();
        graph.putEdge(Node.of("start"), Node.of("A"));
        graph.putEdge(Node.of("start"), Node.of("b"));
        graph.putEdge(Node.of("A"), Node.of("c"));
        graph.putEdge(Node.of("A"), Node.of("b"));
        graph.putEdge(Node.of("b"), Node.of("d"));
        graph.putEdge(Node.of("A"), Node.of("end"));
        graph.putEdge(Node.of("b"), Node.of("end"));
        assertThat(Day12.countPaths(graph)).isEqualTo(10);
    }

    @Test
    public void example2() {
        MutableGraph<Node> graph = GraphBuilder.undirected().build();
        graph.putEdge(Node.of("dc"), Node.of("end"));
        graph.putEdge(Node.of("HN"), Node.of("start"));
        graph.putEdge(Node.of("start"), Node.of("kj"));
        graph.putEdge(Node.of("dc"), Node.of("start"));
        graph.putEdge(Node.of("dc"), Node.of("HN"));
        graph.putEdge(Node.of("LN"), Node.of("dc"));
        graph.putEdge(Node.of("HN"), Node.of("end"));
        graph.putEdge(Node.of("kj"), Node.of("sa"));
        graph.putEdge(Node.of("kj"), Node.of("HN"));
        graph.putEdge(Node.of("kj"), Node.of("dc"));
        assertThat(Day12.countPaths(graph)).isEqualTo(19);
    }

    @Test
    public void example3() {
        MutableGraph<Node> graph = GraphBuilder.undirected().build();
        graph.putEdge(Node.of("fs"), Node.of("end"));
        graph.putEdge(Node.of("he"), Node.of("DX"));
        graph.putEdge(Node.of("fs"), Node.of("he"));
        graph.putEdge(Node.of("start"), Node.of("DX"));
        graph.putEdge(Node.of("pj"), Node.of("DX"));
        graph.putEdge(Node.of("end"), Node.of("zg"));
        graph.putEdge(Node.of("zg"), Node.of("sl"));
        graph.putEdge(Node.of("zg"), Node.of("pj"));
        graph.putEdge(Node.of("pj"), Node.of("he"));
        graph.putEdge(Node.of("RW"), Node.of("he"));
        graph.putEdge(Node.of("fs"), Node.of("DX"));
        graph.putEdge(Node.of("pj"), Node.of("RW"));
        graph.putEdge(Node.of("zg"), Node.of("RW"));
        graph.putEdge(Node.of("start"), Node.of("pj"));
        graph.putEdge(Node.of("he"), Node.of("WI"));
        graph.putEdge(Node.of("zg"), Node.of("he"));
        graph.putEdge(Node.of("pj"), Node.of("fs"));
        graph.putEdge(Node.of("start"), Node.of("RW"));
        assertThat(Day12.countPaths(graph)).isEqualTo(226);
    }

    @Test
    @Override
    public void part1() {
        assertThat(Day12.part1(input)).isEqualTo(3887);
    }

    @Test
    @Override
    public void part2() {
        assertTrue("unimplemented", true);
    }
}
