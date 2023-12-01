package com.github.bertware.aoc2023.day1;


import com.github.bertware.aoc2023.AocTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DayOneChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DayOneChallenge challenge = new DayOneChallenge();
        Assertions.assertEquals(142, challenge.runPart1(getFilePath("day1/part1_example.txt")));
    }

    @Test
    void part1() {
        DayOneChallenge challenge = new DayOneChallenge();
        System.out.println(challenge.runPart1(getFilePath("day1/input.txt")));
    }

    @Test
    void part2_example() {
        DayOneChallenge challenge = new DayOneChallenge();
        Assertions.assertEquals(281, challenge.runPart2(getFilePath("day1/part2_example.txt")));
    }

    @Test
    void part2_ejprxbcghdgxhk9x() {
        DayOneChallenge challenge = new DayOneChallenge();
        Assertions.assertEquals(99, challenge.handleLinePartTwo("jprxbcghdgxhk9x"));
    }

    @Test
    void part2() {
        DayOneChallenge challenge = new DayOneChallenge();
        System.out.println(challenge.runPart2(getFilePath("day1/input.txt")));
    }
}