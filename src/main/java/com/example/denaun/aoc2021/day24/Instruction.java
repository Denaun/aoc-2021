package com.example.denaun.aoc2021.day24;

import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

sealed interface Instruction {
    Variable output();

    void evaluate(Map<Variable, Integer> variables, List<Integer> inputs);
}


record Inp(Variable output, int index) implements Instruction {
    @Override
    public void evaluate(Map<Variable, Integer> variables, List<Integer> inputs) {
        variables.put(output(), inputs.get(index()));
    }
}


sealed interface FixedInstruction extends Instruction, IntUnaryOperator {
    int argument();

    @Override
    default void evaluate(Map<Variable, Integer> variables, List<Integer> inputs) {
        variables.put(output(), applyAsInt(output().applyAsInt(variables)));
    }
}


record Div(Variable output, int argument) implements FixedInstruction {
    @Override
    public int applyAsInt(int left) {
        return left / argument();
    }

    @Override
    public String toString() {
        return "%s /= %s".formatted(output, argument);
    }
}


record Mod(Variable output, int argument) implements FixedInstruction {
    @Override
    public int applyAsInt(int left) {
        return left % argument();
    }

    @Override
    public String toString() {
        return "%s %%= %s".formatted(output, argument);
    }
}


sealed interface BinaryInstruction extends Instruction, IntBinaryOperator {
    Value argument();

    @Override
    default void evaluate(Map<Variable, Integer> variables, List<Integer> inputs) {
        variables.put(output(), applyAsInt(
                output().applyAsInt(variables),
                argument().applyAsInt(variables)));
    }
}



record Add(Variable output, Value argument) implements BinaryInstruction {
    @Override
    public int applyAsInt(int left, int right) {
        return left + right;
    }

    @Override
    public String toString() {
        return "%s += %s".formatted(output, argument);
    }
}


record Mul(Variable output, Value argument) implements BinaryInstruction {
    @Override
    public int applyAsInt(int left, int right) {
        return left * right;
    }

    @Override
    public String toString() {
        return "%s *= %s".formatted(output, argument);
    }
}


record Eql(Variable output, Value argument) implements BinaryInstruction {
    @Override
    public int applyAsInt(int left, int right) {
        return left == right ? 1 : 0;
    }

    @Override
    public String toString() {
        return "%s = %s == %s ? 1 : 0".formatted(output, output, argument);
    }
}

