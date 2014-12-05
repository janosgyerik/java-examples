package com.janosgyerik.examples.util;

import java.io.File;
import java.io.IOException;

public class TestUtils {

    public static void setupCleanDir(File dir) throws IOException {
        wipeDirRecursively(dir);
        if (!dir.mkdirs()) {
            throw new IOException("Could not create directory: " + dir);
        }
    }

    public static void createTestFiles(File dir, String... filenames) throws IOException {
        for (String filename : filenames) {
            new File(dir, filename).createNewFile();
        }
    }

    public static void createTestDirs(File dir, String... dirnames) {
        for (String dirname : dirnames) {
            new File(dir, dirname).mkdirs();
        }
    }

    public static void wipeDirRecursively(File dir) throws IOException {
        String[] items = dir.list();
        if (items != null) {
            for (String item : dir.list()) {
                File file = new File(dir, item);
                if (file.isFile()) {
                    if (!file.delete()) {
                        throw new IOException("Could not delete file: " + file);
                    }
                } else if (file.isDirectory()) {
                    wipeDirRecursively(file);
                }
            }
        }
        if (dir.isDirectory()) {
            if (!dir.delete()) {
                throw new IOException("Could not delete dir: " + dir);
            }
        }
    }
}
