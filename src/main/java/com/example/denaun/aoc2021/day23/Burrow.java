package com.example.denaun.aoc2021.day23;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

record Burrow(List<Room> rooms, Hallway hallway) {
    Burrow {
        checkArgument(rooms.size() == 4);
    }

    Burrow(List<Room> rooms) {
        this(rooms, new Hallway());
    }

    static Burrow copyOf(Burrow other) {
        return new Burrow(
                other.rooms.stream()
                        .map(Room::copyOf)
                        .toList(),
                Hallway.copyOf(other.hallway));
    }

    boolean isDone() {
        return rooms.stream().allMatch(Room::isDone);
    }

    List<WithCost<Burrow>> generateMoves() {
        checkState(!isDone());
        var moves = new ArrayList<WithCost<Burrow>>();
        for (var tileIndex = 0; tileIndex < hallway.tiles().size(); ++tileIndex) {
            moves.addAll(generateMovesFromTileToRoom(tileIndex));
        }
        for (var roomIndex = 0; roomIndex < rooms.size(); ++roomIndex) {
            moves.addAll(generateMovesFromRoomToRoom(roomIndex));
            moves.addAll(generateMovesFromRoomToTile(roomIndex));
        }
        return moves;
    }

    private List<WithCost<Burrow>> generateMovesFromTileToRoom(int tileIndex) {
        if (!hallway.canTake(tileIndex)) {
            return List.of();
        }

        var burrow = copyOf(this);
        var amphipod = burrow.hallway.take(tileIndex);
        var moves = new ArrayList<WithCost<Burrow>>();
        for (var roomIndex = 0; roomIndex < rooms.size(); ++roomIndex) {
            var accessCost = hallway.canAccessRoomFromTile(tileIndex, roomIndex);
            var moveInCost = rooms.get(roomIndex).canAccept(amphipod);
            if (accessCost > 0 && moveInCost > 0) {
                var move = copyOf(burrow);
                move.rooms.get(roomIndex).accept(amphipod);
                moves.add(WithCost.of(
                        move,
                        (accessCost + moveInCost) * amphipod.cost()));
            }
        }
        return moves;
    }

    private List<WithCost<Burrow>> generateMovesFromRoomToRoom(int roomIndex) {
        var moveOutCost = rooms.get(roomIndex).canTake();
        if (moveOutCost <= 0) {
            return List.of();
        }

        var burrow = copyOf(this);
        var amphipod = burrow.rooms.get(roomIndex).take();
        var moves = new ArrayList<WithCost<Burrow>>();
        for (var otherRoomIndex = 0; otherRoomIndex < rooms.size(); ++otherRoomIndex) {
            var accessCost = burrow.hallway.canAccessRoomFromRoom(roomIndex, otherRoomIndex);
            var moveInCost = burrow.rooms.get(otherRoomIndex).canAccept(amphipod);
            if (accessCost > 0 && moveInCost > 0) {
                var move = copyOf(burrow);
                move.rooms.get(otherRoomIndex).accept(amphipod);
                moves.add(WithCost.of(
                        move,
                        (moveOutCost + accessCost + moveInCost) * amphipod.cost()));
            }
        }
        return moves;
    }

    private List<WithCost<Burrow>> generateMovesFromRoomToTile(int roomIndex) {
        var moveOutCost = rooms.get(roomIndex).canTake();
        if (moveOutCost <= 0) {
            return List.of();
        }

        var burrow = copyOf(this);
        var amphipod = burrow.rooms.get(roomIndex).take();
        var moves = new ArrayList<WithCost<Burrow>>();
        for (var tileIndex = 0; tileIndex < hallway.tiles().size(); ++tileIndex) {
            var accessCost = burrow.hallway.canAccessTileFromRoom(roomIndex, tileIndex);
            if (accessCost > 0) {
                var move = copyOf(burrow);
                move.hallway.accessTile(tileIndex, amphipod);
                moves.add(WithCost.of(
                        move,
                        (moveOutCost + accessCost) * amphipod.cost()));
            }
        }
        return moves;
    }
}
