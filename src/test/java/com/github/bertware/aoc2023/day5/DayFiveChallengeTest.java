package com.github.bertware.aoc2023.day5;

import com.github.bertware.aoc2023.AocTest;
import com.github.bertware.aoc2023.day6.DaySixChallenge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DayFiveChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DaySixChallenge challenge = new DaySixChallenge();
        Assertions.assertEquals(35, challenge.part1(getFilePath("day5/example.txt")));
    }

    @Test
    void part1() {
        DaySixChallenge challenge = new DaySixChallenge();
        System.out.println(challenge.part1(getFilePath("day5/input.txt")));
    }

    @Test
    void part2_example() {
        DaySixChallenge challenge = new DaySixChallenge();
        Assertions.assertEquals(46, challenge.part2(getFilePath("day5/example.txt")));
    }

    @Test
    void part2() {
        DaySixChallenge challenge = new DaySixChallenge();
        System.out.println(challenge.part2(getFilePath("day5/input.txt")));
    }
}