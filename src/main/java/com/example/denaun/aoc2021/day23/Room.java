package com.example.denaun.aoc2021.day23;

import com.google.common.base.VerifyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

record Room(Amphipod id, List<Optional<Amphipod>> slots) {
    Room {
        slots = new ArrayList<>(slots);
    }

    static Room copyOf(Room other) {
        return new Room(other.id, other.slots);
    }

    boolean isDone() {
        return slots.stream().allMatch(Optional.of(id)::equals);
    }

    int canTake() {
        return top().map(top -> top.index() + 1).orElse(0);
    }

    Amphipod take() {
        var top = top().orElseThrow();
        slots.set(top.index(), Optional.empty());
        return top.value();
    }

    private Optional<WithIndex<Amphipod>> top() {
        for (var i = 0; i < slots.size(); ++i) {
            var slot = slots.get(i);
            if (slot.isEmpty()) {
                continue;
            }
            if (slots.subList(i, slots.size()).stream()
                    .allMatch(Optional.of(id)::equals)) {
                return Optional.empty();
            }
            return Optional.of(WithIndex.of(slot.get(), i));
        }
        return Optional.empty();
    }

    int canAccept(Amphipod amphipod) {
        if (!id.equals(amphipod)) {
            return 0;
        }
        if (!slots.stream()
                .allMatch(s -> s.isEmpty() || id.equals(s.get()))) {
            return 0;
        }
        return IntStream.range(0, slots.size())
                .map(i -> slots.size() - i - 1)
                .filter(i -> slots.get(i).isEmpty())
                .map(i -> i + 1)
                .findFirst()
                .orElse(0);
    }

    void accept(Amphipod amphipod) {
        for (var i = slots.size() - 1; i >= 0; --i) {
            if (slots.get(i).isEmpty()) {
                slots.set(i, Optional.of(amphipod));
                return;
            }
        }
        throw new VerifyException();
    }
}
