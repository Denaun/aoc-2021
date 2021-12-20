package com.example.denaun.aoc2021.day20;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static org.jparsec.Scanners.isChar;

import com.example.denaun.aoc2021.Matrix;
import com.example.denaun.aoc2021.parsers.AocParsers;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day20Parser {
    private Day20Parser() {}

    public static final Parser<Cell> CELL = Parsers.or(
            isChar('.').retn(Cell.UNLIT),
            isChar('#').retn(Cell.LIT));

    private static final Parser<ImageEnhancementAlgorithm> ALGORITHM = CELL.times(512)
            .map(ImageEnhancementAlgorithm::copyOf);

    private static final Parser<Image> IMAGE = CELL.many1()
            .sepBy1(LINE_ENDING)
            .map(Matrix<Cell>::new)
            .map(Image::new);

    static final Parser<Input> INPUT =
            AocParsers.sepPair(ALGORITHM.followedBy(LINE_ENDING), LINE_ENDING, IMAGE, Input::new)
                    .followedBy(LINE_ENDING);
}
