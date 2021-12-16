package com.example.denaun.aoc2021.day16;

import com.google.common.collect.Streams;
import java.util.stream.Stream;
import org.jparsec.Parser;

public class Day16 {
    private Day16() {}

    private static final Parser<Packet> PARSER = Day16Parser.INPUT;

    static int part1(String input) {
        var packet = PARSER.parse(input);
        return flatten(packet)
                .mapToInt(Packet::version)
                .sum();
    }

    static Stream<Packet> flatten(Packet packet) {
        if (packet instanceof Operator op) {
            return Streams.concat(
                    Stream.of(op),
                    op.subPackets().stream()
                            .flatMap(Day16::flatten));
        } else {
            return Stream.of(packet);
        }
    }

    static long part2(String input) {
        return PARSER.parse(input).value();
    }
}
