package com.github.bertware.aoc2023.day10;

import com.github.bertware.aoc2023.AocTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DayTenChallengeTest extends AocTest {

    @Test
    void part1_example() {
        DayTenChallenge challenge = new DayTenChallenge();
        Assertions.assertEquals(4, challenge.part1(getFilePath("day10/example.txt")));
    }

    @Test
    void part1() {
        DayTenChallenge challenge = new DayTenChallenge();
        System.out.println(challenge.part1(getFilePath("day10/input.txt")));
    }

    @Test
    void part2_example() {
        DayTenChallenge challenge = new DayTenChallenge();
        Assertions.assertEquals(4, challenge.part2(getFilePath("day10/part2_example1.txt")));
        Assertions.assertEquals(4, challenge.part2(getFilePath("day10/part2_example2.txt")));
        Assertions.assertEquals(8, challenge.part2(getFilePath("day10/part2_example3.txt")));
    }

    @Test
    void part2() {
        DayTenChallenge challenge = new DayTenChallenge();
        System.out.println(challenge.part2(getFilePath("day10/input.txt")));
    }
}

