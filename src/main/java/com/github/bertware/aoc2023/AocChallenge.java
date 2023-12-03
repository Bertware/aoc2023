package com.github.bertware.aoc2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.github.bertware.aoc2023.data.AocMatrix;

public abstract class AocChallenge {

    public Stream<String> readLines(Path path) {
        try {
            return Files.readAllLines(path).stream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AocMatrix readMatrix(Path path) {
        try {
            char[][] array = Files.readAllLines(path).stream().map(String::toCharArray).toList().toArray(new char[][]{});
            return new AocMatrix(array);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
