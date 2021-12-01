package com.example.denaun.aoc2021;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import org.junit.Test;

public abstract class AocTestCase {
    protected final String input;

    public AocTestCase(String day) throws IOException {
        input = Resources.toString(Resources.getResource(day), Charsets.UTF_8);
    }

    @Test
    public abstract void part1();

    @Test
    public abstract void part2();
}
