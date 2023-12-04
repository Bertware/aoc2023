package com.github.bertware.aoc2023.day4;

import com.github.bertware.aoc2023.AocTest;
import com.github.bertware.aoc2023.day3.DayThreeChallenge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DayFourChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DayFourChallenge challenge = new DayFourChallenge();
        Assertions.assertEquals(13, challenge.part1(getFilePath("day4/example.txt")));
    }

    @Test
    void part1() {
        DayFourChallenge challenge = new DayFourChallenge();
        System.out.println(challenge.part1(getFilePath("day4/input.txt")));
    }

    @Test
    void part2_example() {
        DayFourChallenge challenge = new DayFourChallenge();
        Assertions.assertEquals(30, challenge.part2(getFilePath("day4/example.txt")));
    }

    @Test
    void part2() {
        DayFourChallenge challenge = new DayFourChallenge();
        System.out.println(challenge.part2(getFilePath("day4/input.txt")));
    }
}