package com.example.denaun.aoc2021.day12;

import static com.google.common.base.Preconditions.checkArgument;

sealed interface Node {
    static Node of(String name) {
        if ("start".equals(name)) {
            return Terminal.START;
        }
        if ("end".equals(name)) {
            return Terminal.END;
        }
        if (name.chars().allMatch(Character::isLowerCase)) {
            return new SmallCave(name);
        }
        checkArgument(name.chars().allMatch(Character::isUpperCase));
        return new LargeCave(name);
    }
}


enum Terminal implements Node {
    START, END;
}


record SmallCave(String name) implements Node {

}


record LargeCave(String name) implements Node {

}
