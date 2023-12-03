package com.github.bertware.aoc2023.day3.day2;

import com.github.bertware.aoc2023.AocTest;
import com.github.bertware.aoc2023.day2.DayTwoChallenge;
import com.github.bertware.aoc2023.day3.DayThreeChallenge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DayThreeChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DayThreeChallenge challenge = new DayThreeChallenge();
        Assertions.assertEquals(4361, challenge.runPart1(getFilePath("day3/example.txt")));
    }

    @Test
    void part1() {
        DayThreeChallenge challenge = new DayThreeChallenge();
        System.out.println(challenge.runPart1(getFilePath("day3/input.txt")));
    }

    @Test
    void part2_example() {
        DayThreeChallenge challenge = new DayThreeChallenge();
        Assertions.assertEquals(467835, challenge.runPart2(getFilePath("day3/example.txt")));
    }

    @Test
    void part2() {
        DayThreeChallenge challenge = new DayThreeChallenge();
        System.out.println(challenge.runPart2(getFilePath("day3/input.txt")));
    }
}