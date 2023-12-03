package com.github.bertware.aoc2023;

import com.github.bertware.aoc2023.data.AocMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AocChallengeTest extends AocTest {

    @Test
    void readMatrix() {
        AocChallenge challenge = new AocChallenge();
        AocMatrix matrix = challenge.readMatrix(getFilePath("matrix.txt"));
        Assertions.assertEquals('1', matrix.charAt(0, 0));
        Assertions.assertEquals('5', matrix.charAt(1, 1));
        Assertions.assertEquals('6', matrix.charAt(1, 2));
        Assertions.assertEquals('9', matrix.charAt(2, 2));

        Assertions.assertEquals(1, matrix.intAt(0, 0));
        Assertions.assertEquals(5, matrix.intAt(1, 1));
        Assertions.assertEquals(6, matrix.intAt(1, 2));
        Assertions.assertEquals(9, matrix.intAt(2, 2));

        Assertions.assertEquals("1", matrix.stringAt(0, 0));
        Assertions.assertEquals("5", matrix.stringAt(1, 1));
        Assertions.assertEquals("6", matrix.stringAt(1, 2));
        Assertions.assertEquals("9", matrix.stringAt(2, 2));
    }

    class AocChallenge extends com.github.bertware.aoc2023.AocChallenge {

    }
}