package com.example.denaun.aoc2021.day18;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

sealed interface Number {
    static Pair sum(Number left, Number right) {
        return Pair.of(left, right);
    }

    static Number valueOf(String string) {
        return Day18Parser.number().parse(string);
    }

    int magnitude();

    Number addLeft(int amount);

    Number addRight(int amount);

    default Optional<Number> tryReducing() {
        return tryExploding(0)
                .map(Explosion::value)
                .or(this::trySplitting);
    }

    Optional<Explosion> tryExploding(int nesting);

    Optional<Pair> trySplitting();

    default Number fullyReduced() {
        var result = this;
        var reduced = result.tryReducing();
        while (reduced.isPresent()) {
            result = reduced.get();
            reduced = result.tryReducing();
        }
        return result;
    }
}


record Regular(int value) implements Number {
    private static final Map<Integer, Regular> REGULARS = new HashMap<>();

    static Regular of(int value) {
        return REGULARS.computeIfAbsent(value, Regular::new);
    }

    @Override
    public int magnitude() {
        return value;
    }

    @Override
    public Number addLeft(int amount) {
        return Regular.of(value + amount);
    }

    @Override
    public Number addRight(int amount) {
        return Regular.of(value + amount);
    }

    @Override
    public Optional<Explosion> tryExploding(int nesting) {
        return Optional.empty();
    }

    @Override
    public Optional<Pair> trySplitting() {
        if (value < 10) {
            return Optional.empty();
        }
        return Optional.of(Pair.of(value / 2, (value + 1) / 2));
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}


record Pair(Number left, Number right) implements Number {
    static Pair of(int left, int right) {
        return new Pair(Regular.of(left), Regular.of(right));
    }

    static Pair of(Number left, int right) {
        return new Pair(left, Regular.of(right));
    }

    static Pair of(int left, Number right) {
        return new Pair(Regular.of(left), right);
    }

    static Pair of(Number left, Number right) {
        return new Pair(left, right);
    }

    @Override
    public int magnitude() {
        return 3 * left.magnitude() + 2 * right.magnitude();
    }

    @Override
    public Number addLeft(int amount) {
        return Pair.of(left.addLeft(amount), right);
    }

    @Override
    public Number addRight(int amount) {
        return Pair.of(left, right.addRight(amount));
    }

    @Override
    public Optional<Explosion> tryExploding(int nesting) {
        if (nesting >= 4
                && left instanceof Regular left
                && right instanceof Regular right) {
            return Optional.of(Explosion.of(left.value(), right.value()));
        }
        var childNesting = nesting + 1;
        return left
                .tryExploding(childNesting)
                .map(e -> Explosion.combine(e, right))
                .or(() -> right
                        .tryExploding(childNesting)
                        .map(e -> Explosion.combine(left, e)));
    }

    @Override
    public Optional<Pair> trySplitting() {
        return left.trySplitting().map(left -> Pair.of(left, right))
                .or(() -> right.trySplitting().map(right -> Pair.of(left, right)));
    }

    @Override
    public String toString() {
        return "[%s,%s]".formatted(left, right);
    }
}


record Explosion(
        Optional<Integer> left,
        Number value,
        Optional<Integer> right) {
    static Explosion of(int left, int right) {
        return new Explosion(Optional.of(left), Regular.of(0), Optional.of(right));
    }

    static Explosion combine(Explosion explosion, Number right) {
        return new Explosion(
                explosion.left(),
                Pair.of(explosion.value(),
                        explosion.right().map(right::addLeft).orElse(right)),
                Optional.empty());
    }

    static Explosion combine(Number left, Explosion explosion) {
        return new Explosion(
                Optional.empty(),
                Pair.of(explosion.left().map(left::addRight).orElse(left),
                        explosion.value()),
                explosion.right());
    }
}
