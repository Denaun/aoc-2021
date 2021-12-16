package com.example.denaun.aoc2021.day16;

import com.google.common.collect.Streams;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OperatorTypes {
    private OperatorTypes() {}

    static final Map<Integer, OperatorType> ID_TO_TYPE =
            Streams.<OperatorType>concat(
                    Stream.of(Reducing.values()),
                    Stream.of(Comparing.values()))
                    .collect(Collectors.toMap(OperatorType::typeId, e -> e));
}
