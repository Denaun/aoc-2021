package com.example.denaun.aoc2021.day16;

sealed interface Packet permits Literal,Operator {
    int version();

    long value();
}
