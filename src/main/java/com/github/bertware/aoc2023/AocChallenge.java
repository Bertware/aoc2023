package com.github.bertware.aoc2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class AocChallenge {

    public Stream<String> readLines(Path path) {
        try {
            return Files.readAllLines(path).stream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
