package com.example.denaun.aoc2021.day10;

enum Token {
    // @formatter:off
    OPEN_ROUND(TokenFunction.OPEN, ChunkType.ROUND),
    CLOSE_ROUND(TokenFunction.CLOSE, ChunkType.ROUND),
    OPEN_SQUARE(TokenFunction.OPEN, ChunkType.SQUARE),
    CLOSE_SQUARE(TokenFunction.CLOSE, ChunkType.SQUARE),
    OPEN_CURLY(TokenFunction.OPEN, ChunkType.CURLY),
    CLOSE_CURLY(TokenFunction.CLOSE, ChunkType.CURLY),
    OPEN_ANGLE(TokenFunction.OPEN, ChunkType.ANGLE),
    CLOSE_ANGLE(TokenFunction.CLOSE, ChunkType.ANGLE);
    // @formatter:on

    private final TokenFunction function;
    private final ChunkType type;

    Token(TokenFunction function, ChunkType type) {
        this.function = function;
        this.type = type;
    }

    TokenFunction function() {
        return function;
    }

    ChunkType type() {
        return type;
    }
}
