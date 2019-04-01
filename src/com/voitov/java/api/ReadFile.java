package com.voitov.java.api;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {

    public String readFile(String filePath) {
        StringBuilder log = new StringBuilder();
        try {
            Files.lines(Paths.get(filePath))
                    .filter(v -> !v.trim().isEmpty())
                    .forEach(x -> log.append(x).append("\n"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return log.toString();
    }
}
