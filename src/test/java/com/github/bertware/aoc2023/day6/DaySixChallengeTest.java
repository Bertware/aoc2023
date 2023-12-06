package com.github.bertware.aoc2023.day6;

import com.github.bertware.aoc2023.AocTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DaySixChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DaySixChallenge challenge = new DaySixChallenge();
        Assertions.assertEquals(288, challenge.part1(getFilePath("day6/example.txt")));
    }

    @Test
    void part1() {
        DaySixChallenge challenge = new DaySixChallenge();
        System.out.println(challenge.part1(getFilePath("day6/input.txt")));
    }

    @Test
    void part2_example() {
        DaySixChallenge challenge = new DaySixChallenge();
        Assertions.assertEquals(71503, challenge.part2(getFilePath("day6/example.txt")));
    }

    @Test
    void part2() {
        DaySixChallenge challenge = new DaySixChallenge();
        System.out.println(challenge.part2(getFilePath("day6/input.txt")));
    }
}