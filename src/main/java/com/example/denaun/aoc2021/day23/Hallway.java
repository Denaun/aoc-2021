package com.example.denaun.aoc2021.day23;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

record Hallway(List<Optional<Amphipod>> tiles) {
    private static final int NUM_TILES = 11;
    private static final int MARGIN = 2;
    private static final int SPACING = 2;

    Hallway {
        checkArgument(tiles.size() == NUM_TILES);
        tiles = new ArrayList<>(tiles);
    }

    Hallway() {
        this(Collections.nCopies(NUM_TILES, Optional.empty()));
    }

    static Hallway copyOf(Hallway other) {
        return new Hallway(other.tiles);
    }

    boolean canTake(int tileIndex) {
        return tiles.get(tileIndex).isPresent();
    }

    Amphipod take(int tileIndex) {
        return tiles.set(tileIndex, Optional.empty()).orElseThrow();
    }

    int canAccessRoomFromRoom(int fromRoom, int toRoom) {
        var startIndex = tileIndex(fromRoom);
        var endIndex = tileIndex(toRoom);
        return canMove(startIndex, endIndex, false);
    }

    int canAccessRoomFromTile(int fromTile, int toRoom) {
        var endIndex = tileIndex(toRoom);
        return canMove(fromTile, endIndex, false);
    }

    void accessTile(int tileIndex, Amphipod amphipod) {
        checkArgument(tiles.get(tileIndex).isEmpty());
        tiles.set(tileIndex, Optional.of(amphipod));
    }

    int canAccessTileFromRoom(int fromRoom, int toTile) {
        var startIndex = tileIndex(fromRoom);
        return canMove(startIndex, toTile, true);
    }

    private static int tileIndex(int roomIndex) {
        return MARGIN + roomIndex * SPACING;
    }

    private int canMove(int fromTile, int toTile, boolean targetIsTile) {
        if (targetIsTile
                && toTile >= MARGIN
                && toTile <= tiles.size() - MARGIN
                && toTile % SPACING == 0) {
            // Not accessible (directly in front of a room)
            return 0;
        }
        if (fromTile == toTile) {
            return 0;
        }
        int cost;
        if (fromTile < toTile) {
            cost = toTile - fromTile;
            fromTile += 1;
        } else {
            cost = fromTile - toTile;
            var temp = fromTile;
            fromTile = toTile;
            toTile = temp - 1;
        }
        if (tiles.subList(fromTile, toTile + 1).stream()
                .anyMatch(Optional::isPresent)) {
            return 0;
        }
        return cost;
    }
}
