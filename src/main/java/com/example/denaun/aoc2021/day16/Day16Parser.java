package com.example.denaun.aoc2021.day16;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static com.example.denaun.aoc2021.parsers.AocScanners.HEXADECIMAL;
import static org.jparsec.Scanners.isChar;
import static org.jparsec.Scanners.string;

import com.example.denaun.aoc2021.parsers.AocPredicates;
import com.google.common.collect.Streams;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.pattern.Patterns;

class Day16Parser {
    private Day16Parser() {}

    static final Parser<String> BITS = HEXADECIMAL
            .map(str -> {
                var binary = new BigInteger(str, 16).toString(2);
                var expectedLength = str.length() * 4;
                if (expectedLength > binary.length()) {
                    return "0".repeat(expectedLength - binary.length())
                            + binary;
                }
                return binary;
            });


    private static Parser<Integer> bits(int n, String name) {
        return rawBits(n, name).map(s -> Integer.valueOf(s, 2));
    }

    private static Parser<String> rawBits(int n, String name) {
        return Patterns.repeat(n, AocPredicates.IS_BINARY)
                .toScanner(name).source();
    }

    private static final Parser<Integer> VERSION = bits(3, "version");

    private static final Parser<Integer> LITERAL_GROUP = bits(4, "literal group");
    private static final Parser<Integer> LITERAL_VALUE = Parsers.sequence(
            isChar('1').next(LITERAL_GROUP).many(),
            isChar('0').next(LITERAL_GROUP),
            (groups, last) -> Streams
                    .concat(groups.stream(), Stream.of(last))
                    .mapToInt(i -> i)
                    .reduce((a, b) -> a << 4 | b)
                    .orElseThrow());
    private static final Parser<Literal> LITERAL = Parsers.sequence(
            VERSION.followedBy(string("100")),
            LITERAL_VALUE,
            Literal::new);

    private static final Parser<Integer> OPERATOR = bits(3, "operator");
    private static final Parser<Integer> TOTAL_LENGTH = bits(15, "total length");
    private static final Parser<Integer> NUM_SUB_PACKETS = bits(11, "number of sub-packets");

    private static Parser<List<Packet>> subPackets(Parser<Packet> packet) {
        return Parsers.or(
                isChar('0')
                        .next(TOTAL_LENGTH)
                        .next(length -> rawBits(length, "sub-packets"))
                        .map(packet.many()::parse),
                isChar('1')
                        .next(NUM_SUB_PACKETS)
                        .next(packet::times));
    }

    private static Parser<Operator> operator(Parser<Packet> packet) {
        return Parsers.sequence(
                VERSION,
                OPERATOR,
                subPackets(packet),
                Operator::new);
    }

    static Parser<Packet> packet() {
        var ref = Parser.<Packet>newReference();
        var parser = Parsers.<Packet>or(
                LITERAL,
                operator(ref.lazy()));
        ref.set(parser);
        return parser;
    }

    static final Parser<List<Packet>> INPUT = BITS.followedBy(LINE_ENDING)
            .map(packet().many1()
                    .followedBy(isChar('0').many())::parse);
}
