package com.example.denaun.aoc2021.day23;

import static org.jparsec.Scanners.isChar;
import static org.jparsec.Scanners.string;

import java.util.List;
import java.util.Optional;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day23Parser {
    private Day23Parser() {}

    private static final Parser<Amphipod> AMPHIPOD = Parsers.or(
            isChar('A').retn(Amphipod.AMBER),
            isChar('B').retn(Amphipod.BRONZE),
            isChar('C').retn(Amphipod.COPPER),
            isChar('D').retn(Amphipod.DESERT));

    static final Parser<List<Room>> INPUT = Parsers.sequence(
            string("""
                    #############
                    #...........#
                    ###""").next(AMPHIPOD),
            isChar('#').next(AMPHIPOD),
            isChar('#').next(AMPHIPOD),
            isChar('#').next(AMPHIPOD),
            string("""
                    ###
                      #""").next(AMPHIPOD),
            isChar('#').next(AMPHIPOD),
            isChar('#').next(AMPHIPOD),
            isChar('#').next(AMPHIPOD)
                    .followedBy(string("""
                            #
                              #########
                            """)),
            (a0, b0, c0, d0, a1, b1, c1, d1) -> List.of(
                    new Room(Amphipod.AMBER, List.of(Optional.of(a0), Optional.of(a1))),
                    new Room(Amphipod.BRONZE, List.of(Optional.of(b0), Optional.of(b1))),
                    new Room(Amphipod.COPPER, List.of(Optional.of(c0), Optional.of(c1))),
                    new Room(Amphipod.DESERT, List.of(Optional.of(d0), Optional.of(d1)))));
}
