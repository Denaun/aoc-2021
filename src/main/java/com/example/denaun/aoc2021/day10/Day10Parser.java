package com.example.denaun.aoc2021.day10;

import static com.example.denaun.aoc2021.parsers.AocParsers.LINE_ENDING;
import static org.jparsec.Scanners.isChar;

import java.util.List;
import org.jparsec.Parser;
import org.jparsec.Parsers;

class Day10Parser {
    private Day10Parser() {}

    static final Parser<Token> TOKEN = Parsers.or(
            isChar('(').retn(Token.OPEN_ROUND),
            isChar(')').retn(Token.CLOSE_ROUND),
            isChar('[').retn(Token.OPEN_SQUARE),
            isChar(']').retn(Token.CLOSE_SQUARE),
            isChar('{').retn(Token.OPEN_CURLY),
            isChar('}').retn(Token.CLOSE_CURLY),
            isChar('<').retn(Token.OPEN_ANGLE),
            isChar('>').retn(Token.CLOSE_ANGLE));
    static final Parser<List<Token>> LINE = TOKEN.many1();
    static final Parser<List<List<Token>>> INPUT = LINE.endBy1(LINE_ENDING);
}
