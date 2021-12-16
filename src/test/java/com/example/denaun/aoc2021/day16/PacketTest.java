package com.example.denaun.aoc2021.day16;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class PacketTest {
    @Test
    public void example5() {
        assertThat(Day16Parser.INPUT.parse("C200B40A82\n").value()).isEqualTo(3);
        assertThat(Day16Parser.INPUT.parse("04005AC33890\n").value()).isEqualTo(54);
        assertThat(Day16Parser.INPUT.parse("880086C3E88112\n").value()).isEqualTo(7);
        assertThat(Day16Parser.INPUT.parse("CE00C43D881120\n").value()).isEqualTo(9);
        assertThat(Day16Parser.INPUT.parse("D8005AC2A8F0\n").value()).isEqualTo(1);
        assertThat(Day16Parser.INPUT.parse("F600BC2D8F\n").value()).isEqualTo(0);
        assertThat(Day16Parser.INPUT.parse("9C005AC2F8F0\n").value()).isEqualTo(0);
        assertThat(Day16Parser.INPUT.parse("9C0141080250320F1802104A08\n").value()).isEqualTo(1);
    }
}
