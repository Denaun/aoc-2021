package com.example.denaun.aoc2021.day20;

import java.util.List;

class ImageEnhancementAlgorithm {
    private final List<Cell> cells;

    private ImageEnhancementAlgorithm(List<Cell> cells) {
        this.cells = cells;
    }

    static ImageEnhancementAlgorithm copyOf(List<Cell> cells) {
        return new ImageEnhancementAlgorithm(List.copyOf(cells));
    }

    Cell get(int index) {
        return cells.get(index);
    }
}
