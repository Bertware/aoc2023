package com.github.bertware.aoc2023.day2;

import com.github.bertware.aoc2023.AocTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DayTwoChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DayTwoChallenge challenge = new DayTwoChallenge();
        Assertions.assertEquals(8, challenge.runPart1(getFilePath("day2/example.txt"), 12, 13, 14));
    }

    @Test
    void part1() {
        DayTwoChallenge challenge = new DayTwoChallenge();
        System.out.println(challenge.runPart1(getFilePath("day2/input.txt"), 12, 13, 14));
    }

    @Test
    void part2_example() {
        DayTwoChallenge challenge = new DayTwoChallenge();
        Assertions.assertEquals(2286, challenge.runPart2(getFilePath("day2/example.txt")));
    }

    @Test
    void part2() {
        DayTwoChallenge challenge = new DayTwoChallenge();
        System.out.println(challenge.runPart2(getFilePath("day2/input.txt")));
    }
}