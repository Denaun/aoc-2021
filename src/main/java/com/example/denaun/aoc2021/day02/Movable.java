package com.example.denaun.aoc2021.day02;

public interface Movable<T> {
    T forward(int step);

    T down(int step);

    T up(int step);
}
