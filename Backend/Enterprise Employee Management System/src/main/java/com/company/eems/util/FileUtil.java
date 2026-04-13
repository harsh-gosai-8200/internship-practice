package com.company.eems.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Utility class for file read/write operations.
 */
public final class FileUtil {

    private FileUtil() {}

    /**
     * Writes lines to a file.
     *
     * @param filePath file path
     * @param lines content lines
     * @throws IOException if write fails
     */
    public static void writeLines(String filePath, List<String> lines)
            throws IOException {

        Path path = Paths.get(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    /**
     * Reads all lines from a file.
     *
     * @param filePath file path
     * @return list of lines
     * @throws IOException if read fails
     */
    public static List<String> readLines(String filePath)
            throws IOException {

        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        return Files.readAllLines(path);
    }
}
