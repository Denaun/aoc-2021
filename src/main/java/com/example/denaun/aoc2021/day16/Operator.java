package com.example.denaun.aoc2021.day16;

import java.util.List;

record Operator(
        int version,
        OperatorType type,
        List<Packet> subPackets) implements Packet {

    @Override
    public long value() {
        return type.value(subPackets);
    }
}
