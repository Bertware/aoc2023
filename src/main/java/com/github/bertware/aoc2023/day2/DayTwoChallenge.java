package com.github.bertware.aoc2023.day2;

import com.github.bertware.aoc2023.AocChallenge;

import java.nio.file.Path;

public class DayTwoChallenge extends AocChallenge {


    public int runPart1(Path inputFilePath, int red, int green, int blue) {
        return readLines(inputFilePath).mapToInt(line -> handleLinePartOne(line, red, green, blue)).sum();
    }

    private int handleLinePartOne(String line, int red, int green, int blue) {
        String[] gameDrawsSplit = line.split(": ");
        int gameId = Integer.parseInt(gameDrawsSplit[0].split(" ")[1]);
        for (String draw : gameDrawsSplit[1].split("; ")) {
            if (!isDrawPossible(draw, red, green, blue)) {
                return 0;
            }
        }

        return gameId;
    }

    private boolean isDrawPossible(String draw, int red, int green, int blue) {
        for (String colorInDraw : draw.split(", ")) {
            String[] countAndColor = colorInDraw.split(" ");
            int count = Integer.valueOf(countAndColor[0].trim());
            String color = countAndColor[1];
            switch (color) {
                case "red":
                    if (count > red) {
                        return false;
                    }
                    break;
                case "blue":
                    if (count > blue) {
                        return false;
                    }
                    break;
                case "green":
                    if (count > green) {
                        return false;
                    }
            }
        }
        return true;
    }


    public int runPart2(Path inputFilePath) {
        return readLines(inputFilePath).mapToInt(this::handleLinePartTwo).sum();
    }

    public int handleLinePartTwo(String line) {
        int r = 0;
        int g = 0;
        int b = 0;
        String[] draws = line.split(": ")[1].split("; ");
        for (String draw : draws) {
            for (String colorInDraw : draw.split(", ")) {
                String[] countAndColor = colorInDraw.split(" ");
                int count = Integer.valueOf(countAndColor[0].trim());
                String color = countAndColor[1];
                switch (color) {
                    case "red":
                        r = Math.max(r, count);
                        break;
                    case "blue":
                        b = Math.max(b, count);
                        break;
                    case "green":
                        g = Math.max(g, count);
                }
            }
        }
        return r * g * b;
    }
}
