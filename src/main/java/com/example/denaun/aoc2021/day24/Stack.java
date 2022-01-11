package com.example.denaun.aoc2021.day24;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Verify.verify;

import com.google.common.collect.Streams;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Stack {
    private final List<IntWithIndex> pop;
    private final List<Constraint> constraints;
    private final List<IntWithIndex> push;

    private Stack(List<IntWithIndex> pop, List<Constraint> constraints, List<IntWithIndex> push) {
        this.pop = pop;
        this.constraints = constraints;
        this.push = push;
    }

    static Stack pop(int correction) {
        return new Stack(List.of(IntWithIndex.of(correction, 0)), List.of(), List.of());
    }

    static Stack push(int offset) {
        return new Stack(List.of(), List.of(), List.of(IntWithIndex.of(offset, 0)));
    }

    List<Constraint> getAsConstraints() {
        checkState(pop.isEmpty());
        checkState(push.isEmpty());
        return constraints;
    }

    int size() {
        return pop.size() + 2 * constraints.size() + push.size();
    }

    Stack offsetBy(int offset) {
        return new Stack(
                pop.stream().map(e -> e.offsetBy(offset)).toList(),
                constraints.stream().map(e -> e.offsetBy(offset)).toList(),
                push.stream().map(e -> e.offsetBy(offset)).toList());
    }

    static Stack combine(Stack a, Stack b) {
        b = b.offsetBy(a.size());
        if (a.push.size() >= b.pop.size()) {
            var uncombined = a.push.size() - b.pop.size();
            return new Stack(
                    a.pop,
                    Streams.concat(
                            a.constraints.stream(),
                            combine(a.push.subList(uncombined, a.push.size()),
                                    b.pop),
                            b.constraints.stream())
                            .toList(),
                    Streams.concat(
                            a.push.subList(0, uncombined).stream(),
                            b.push.stream())
                            .toList());
        } else {
            var uncombined = b.pop.size() - a.push.size();
            return new Stack(
                    Streams.concat(
                            b.pop.subList(0, uncombined).stream(),
                            a.pop.stream())
                            .toList(),
                    Streams.concat(
                            a.constraints.stream(),
                            combine(a.push,
                                    b.pop.subList(uncombined, b.pop.size())),
                            b.constraints.stream())
                            .toList(),
                    b.push);
        }
    }

    private static Stream<Constraint> combine(List<IntWithIndex> push, List<IntWithIndex> pop) {
        verify(push.size() == pop.size());
        return IntStream.range(0, push.size())
                .mapToObj(ix -> new Constraint(
                        push.get(ix).value() + pop.get(ix).value(),
                        push.get(ix).index(), pop.get(ix).index()));
    }
}
