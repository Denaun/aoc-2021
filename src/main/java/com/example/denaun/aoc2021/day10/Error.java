package com.example.denaun.aoc2021.day10;

import java.util.List;

sealed interface Error {
}


final record Corrupted(ChunkType type) implements Error {
}


final record Incomplete(List<ChunkType> missing) implements Error {
}
