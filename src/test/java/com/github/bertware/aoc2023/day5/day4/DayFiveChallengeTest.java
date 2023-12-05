package com.github.bertware.aoc2023.day5.day4;

import com.github.bertware.aoc2023.AocTest;
import com.github.bertware.aoc2023.day4.DayFiveChallenge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DayFiveChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DayFiveChallenge challenge = new DayFiveChallenge();
        Assertions.assertEquals(35, challenge.part1(getFilePath("day5/example.txt")));
    }

    @Test
    void part1() {
        DayFiveChallenge challenge = new DayFiveChallenge();
        System.out.println(challenge.part1(getFilePath("day5/input.txt")));
    }

    @Test
    void part2_example() {
        DayFiveChallenge challenge = new DayFiveChallenge();
        Assertions.assertEquals(46, challenge.part2(getFilePath("day5/example.txt")));
    }

    @Test
    void part2() {
        DayFiveChallenge challenge = new DayFiveChallenge();
        System.out.println(challenge.part2(getFilePath("day5/input.txt")));
    }
}