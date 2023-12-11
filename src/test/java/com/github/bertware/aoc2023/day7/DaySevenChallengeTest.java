package com.github.bertware.aoc2023.day7;

import com.github.bertware.aoc2023.AocTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DaySevenChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DaySevenChallenge challenge = new DaySevenChallenge();
        Assertions.assertEquals(6440, challenge.part1(getFilePath("day7/example.txt")));
    }

    @Test
    void part1() {
        DaySevenChallenge challenge = new DaySevenChallenge();
        int x = challenge.part1(getFilePath("day7/input.txt"));
        System.out.println(x);
    }

    @Test
    void part2_example() {
        DaySevenChallenge2 challenge = new DaySevenChallenge2();
        Assertions.assertEquals(5905, challenge.part2(getFilePath("day7/example.txt")));
    }

    @Test
    void part2() {
        DaySevenChallenge2 challenge = new DaySevenChallenge2();
        int x = challenge.part2(getFilePath("day7/input.txt"));
        Assertions.assertTrue(x < 247887755, String.valueOf(x));
        System.out.println(x);
    }
}