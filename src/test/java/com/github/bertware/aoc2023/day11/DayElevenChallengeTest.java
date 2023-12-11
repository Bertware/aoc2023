package com.github.bertware.aoc2023.day11;

import com.github.bertware.aoc2023.AocTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DayElevenChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DayElevenChallenge challenge = new DayElevenChallenge();
        Assertions.assertEquals(374, challenge.part1(getFilePath("day11/example.txt")));
    }

    @Test
    void part1() {
        DayElevenChallenge challenge = new DayElevenChallenge();
        System.out.println(challenge.part1(getFilePath("day11/input.txt")));
    }

    @Test
    void part2_example() {
        DayElevenChallenge challenge = new DayElevenChallenge();
        Assertions.assertEquals(1030, challenge.getDistances(getFilePath("day11/example.txt"), 10));
        Assertions.assertEquals(8410, challenge.getDistances(getFilePath("day11/example.txt"), 100));
    }

    @Test
    void part2() {
        DayElevenChallenge challenge = new DayElevenChallenge();
        System.out.println(challenge.part2(getFilePath("day11/input.txt")));
    }
}

