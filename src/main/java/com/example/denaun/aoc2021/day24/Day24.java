package com.example.denaun.aoc2021.day24;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.jparsec.Parser;

class Day24 {
    private Day24() {}

    private static final Supplier<Parser<List<Instruction>>> PARSER = Day24Parser::input;

    enum Preference implements Consumer<List<Integer>> {
        LARGEST {
            @Override
            public void accept(List<Integer> input) {
                input.set(input.size() - 1, 9);
            }
        },
        SMALLEST {
            @Override
            public void accept(List<Integer> input) {
                input.set(input.size() - 1, 1);
            }
        };
    }

    static long part1(String input) {
        var instructions = PARSER.get().parse(input);
        return crack(instructions, Preference.LARGEST);
    }

    static long part2(String input) {
        var instructions = PARSER.get().parse(input);
        return crack(instructions, Preference.SMALLEST);
    }

    static long crack(List<Instruction> instructions, Consumer<List<Integer>> preference) {
        var input = new ArrayList<Integer>();
        for (var i = 0; i < 14; ++i) {
            var threshold = run(instructions, input) / 10;
            input.add(0);
            if (!searchImprovement(instructions, input, i, threshold)) {
                preference.accept(input);
                for (var j = 0; j < i; ++j) {
                    var prev = input.get(j);
                    if (searchImprovement(instructions, input, j, threshold)) {
                        break;
                    }
                    input.set(j, prev);
                }
            }
        }
        checkState(run(instructions, input) == 0, "No solution found");
        return input.stream()
                .mapToLong(i -> i)
                .reduce(0, (total, digit) -> total * 10 + digit);
    }

    private static boolean searchImprovement(
            List<Instruction> instructions, List<Integer> input,
            int index, int threshold) {
        for (var digit = 1; digit < 10; ++digit) {
            input.set(index, digit);
            if (run(instructions, input) < threshold) {
                return true;
            }
        }
        return false;
    }

    static int run(List<Instruction> instructions, List<Integer> input) {
        var variables = new EnumMap<Variable, Integer>(Variable.class);
        for (var variable : Variable.values()) {
            variables.put(variable, 0);
        }
        for (var instruction : instructions) {
            try {
                instruction.evaluate(variables, input);
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return variables.get(Variable.Z);
    }

}
