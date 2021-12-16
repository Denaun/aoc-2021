package com.example.denaun.aoc2021.day16;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import org.junit.Test;

public class Day16ParserTest {
    @Test
    public void example1() {
        assertThat(Day16Parser.BITS.parse("D2FE28"))
                .isEqualTo("110100101111111000101000");
        assertThat(Day16Parser.packet().parse("110100101111111000101"))
                .isEqualTo(new Literal(6, 2021));
    }

    @Test
    public void example2() {
        assertThat(Day16Parser.BITS.parse("38006F45291200"))
                .isEqualTo("00111000000000000110111101000101001010010001001000000000");
        assertThat(Day16Parser.packet()
                .parse("0011100000000000011011110100010100101001000100100"))
                        .isEqualTo(new Operator(1, Comparing.LESS_THAN,
                                List.of(new Literal(6, 10),
                                        new Literal(2, 20))));
    }

    @Test
    public void example3() {
        assertThat(Day16Parser.BITS.parse("EE00D40C823060"))
                .isEqualTo("11101110000000001101010000001100100000100011000001100000");
        assertThat(Day16Parser.packet()
                .parse("111011100000000011010100000011001000001000110000011"))
                        .isEqualTo(new Operator(7, Reducing.MAXIMUM,
                                List.of(new Literal(2, 1),
                                        new Literal(4, 2),
                                        new Literal(1, 3))));
    }
}
