package com.github.bertware.aoc2023.day8;

import com.github.bertware.aoc2023.AocTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DayEightChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DayEightChallenge challenge = new DayEightChallenge();
        Assertions.assertEquals(6, challenge.part1(getFilePath("day8/example.txt")));
    }

    @Test
    void part1() {
        DayEightChallenge challenge = new DayEightChallenge();
        System.out.println(challenge.part1(getFilePath("day8/input.txt")));
    }

    @Test
    void part2_example() {
        DayEightChallenge challenge = new DayEightChallenge();
        Assertions.assertEquals(6, challenge.part2(getFilePath("day8/part2_example.txt")));
    }

    @Test
    void part2() {
        DayEightChallenge challenge = new DayEightChallenge();
        System.out.println(challenge.part2(getFilePath("day8/input.txt")));
    }
}

