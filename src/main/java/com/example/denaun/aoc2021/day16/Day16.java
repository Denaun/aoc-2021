package com.example.denaun.aoc2021.day16;

import com.google.common.collect.Streams;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.jparsec.Parser;

public class Day16 {
    private Day16() {}

    private static final Parser<List<Packet>> PARSER = Day16Parser.INPUT;

    static int part1(String input) {
        var packets = PARSER.parse(input);
        return flatten(packets)
                .mapToInt(Packet::version)
                .sum();
    }

    static Stream<Packet> flatten(Collection<Packet> packets) {
        return packets.stream()
                .flatMap(packet -> {
                    if (packet instanceof Operator op) {
                        return Streams.concat(
                                Stream.of(op),
                                flatten(op.subPackets()));
                    } else {
                        return Stream.of(packet);
                    }
                });
    }
}
