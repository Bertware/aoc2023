package com.github.bertware.aoc2023.day9;

import com.github.bertware.aoc2023.AocTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DayNineChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DayNineChallenge challenge = new DayNineChallenge();
        Assertions.assertEquals(18, challenge.getNextValue(List.of(0, 3, 6, 9, 12, 15)));
        Assertions.assertEquals(28, challenge.getNextValue(List.of(1, 3, 6, 10, 15, 21)));
        Assertions.assertEquals(114, challenge.part1(getFilePath("day9/example.txt")));
    }

    @Test
    void part1() {
        DayNineChallenge challenge = new DayNineChallenge();
        System.out.println(challenge.part1(getFilePath("day9/input.txt")));
    }

    @Test
    void part2_example() {
        DayNineChallenge challenge = new DayNineChallenge();
        Assertions.assertEquals(-3, challenge.getPreviousValue(List.of(0, 3, 6, 9, 12, 15)));
        Assertions.assertEquals(0, challenge.getPreviousValue(List.of(1, 3, 6, 10, 15, 21)));
        Assertions.assertEquals(5, challenge.getPreviousValue(List.of(10, 13, 16, 21, 30, 45)));
        Assertions.assertEquals(2, challenge.part2(getFilePath("day9/example.txt")));
    }

    @Test
    void part2() {
        DayNineChallenge challenge = new DayNineChallenge();
        System.out.println(challenge.part2(getFilePath("day9/input.txt")));
    }
}

