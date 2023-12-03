package com.github.bertware.aoc2023;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;

public abstract class AocTest {

    public Path getFilePath(String filename) {
        try {
            return new File(getClass().getClassLoader().getResource(filename).toURI()).toPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
