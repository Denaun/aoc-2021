package com.example.denaun.aoc2021.day16;

import java.util.List;

sealed interface Packet {
    int version();
}


record Literal(int version, int value) implements Packet {
}


record Operator(
        int version, int operator,
        List<Packet> subPackets) implements Packet {
}
