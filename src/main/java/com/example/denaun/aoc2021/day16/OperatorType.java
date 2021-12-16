package com.example.denaun.aoc2021.day16;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.function.ToLongFunction;
import java.util.stream.LongStream;

sealed interface OperatorType {
    static Optional<OperatorType> valueOf(int typeId) {
        return Optional.ofNullable(OperatorTypes.ID_TO_TYPE.get(typeId));
    }

    int typeId();

    long value(List<Packet> packets);
}


enum Reducing implements OperatorType {
    // @formatter:off
    SUM(0, LongStream::sum),
    PRODUCT(1, values -> values.reduce(1, (x, y) -> x * y)),
    MINIMUM(2, values -> values.min().orElseThrow()),
    MAXIMUM(3, values -> values.max().orElseThrow());
    // @formatter:on

    private final int typeId;
    private final ToLongFunction<LongStream> reduce;

    Reducing(int typeId, ToLongFunction<LongStream> reduce) {
        this.typeId = typeId;
        this.reduce = reduce;
    }

    @Override
    public int typeId() {
        return typeId;
    }

    @Override
    public long value(List<Packet> packets) {
        return reduce.applyAsLong(packets.stream().mapToLong(Packet::value));
    }
}


enum Comparing implements OperatorType {
    // @formatter:off
    GREATER_THAN(5, v -> v > 0),
    LESS_THAN(6, v -> v < 0),
    EQUAL_TO(7, v -> v == 0);
    // @formatter:on

    private final int typeId;
    private final IntPredicate comparison;
    private static final Comparator<Packet> comparator =
            Comparator.comparingLong(Packet::value);

    Comparing(int typeId, IntPredicate comparison) {
        this.typeId = typeId;
        this.comparison = comparison;
    }

    @Override
    public int typeId() {
        return typeId;
    }

    @Override
    public long value(List<Packet> packets) {
        checkArgument(packets.size() == 2);

        if (comparison.test(
                comparator.compare(packets.get(0), packets.get(1)))) {
            return 1;
        }
        return 0;
    }
}
