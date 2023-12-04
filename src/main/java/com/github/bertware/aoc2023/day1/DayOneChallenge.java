package com.github.bertware.aoc2023.day1;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.github.bertware.aoc2023.AocChallenge;

public class DayOneChallenge extends AocChallenge {
    Map<String, Character> digits = Map.of(
            "one", '1',
            "two", '2',
            "three", '3',
            "four", '4',
            "five", '5',
            "six", '6',
            "seven", '7',
            "eight", '8',
            "nine", '9'
    );

    public int runPart1(Path inputFilePath) {
        return readLines(inputFilePath).stream().mapToInt(this::handleLinePartOne).sum();
    }

    private int handleLinePartOne(String line) {
        List<String> characters = line.chars().filter(Character::isDigit).boxed().map(Character::toString).toList();
        String number = characters.getFirst() + characters.getLast();
        return Integer.parseInt(number);
    }


    public int runPart2(Path inputFilePath) {
        return readLines(inputFilePath).stream().mapToInt(this::handleLinePartTwo).sum();
    }

    int handleLinePartTwo(String line) {
        Character firstDigit = null;
        Character lastDigit = null;
        int i = 0;
        // Forward search for first
        while (i < line.length() && firstDigit == null) {
            firstDigit = findDigit(line, i);
            i++;
        }
        // Backward search for last, without overlapping with the range searched previously.
        for (int j = line.length() - 1; j >= (i - 1) && lastDigit == null; j--) {
            lastDigit = findDigit(line, j);
        }
        lastDigit = lastDigit == null ? firstDigit : lastDigit;
        return Integer.parseInt("" + firstDigit + lastDigit);
    }

    private Character findDigit(String line, int i) {
        char c = line.charAt(i);
        if (Character.isDigit(c)) {
            return c;
        }
        for (Map.Entry<String, Character> spelledDigit : digits.entrySet()) {
            String spelledValue = spelledDigit.getKey(); // The string charValue, "one"
            if (spelledValue.charAt(0) != c) { // Doesn't even start with the right character
                continue;
            }

            int remainingLength = line.length() - i; // The remaining length in the line from the character index
            boolean fitsSpelledDigit = remainingLength >= spelledValue.length(); // Whether the string charValue fits in the remaining length
            if (fitsSpelledDigit && line.substring(i, i + spelledValue.length()).equals(spelledValue)) {
                return spelledDigit.getValue();
            }
        }
        return null;
    }

}
