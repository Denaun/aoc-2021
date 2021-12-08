package com.example.denaun.aoc2021.day08;

import java.util.EnumSet;
import java.util.List;

record DisplayObservation(List<EnumSet<Signal>> training, List<EnumSet<Signal>> output) {
}
