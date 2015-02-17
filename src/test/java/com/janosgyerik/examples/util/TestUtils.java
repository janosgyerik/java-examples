package com.janosgyerik.examples.util;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestUtils {

    public static void setupCleanDir(File path) throws IOException {
        if (path.exists()) {
            if (!path.isDirectory()) {
                throw new IOException("Specified path should not exist or be a directory: " + path);
            }
            FileUtils.deleteRecursively(path);
        }
        if (!path.mkdirs()) {
            throw new IOException("Could not create directory: " + path);
        }
    }

    public static void createTestFiles(File basedir, String... filenames) throws IOException {
        for (String filename : filenames) {
            File file = new File(basedir, filename);
            if (!file.createNewFile()) {
                throw new IOException("Could not create file: " + file);
            }
        }
    }

    public static void createTestDirs(File basedir, String... dirnames) throws IOException {
        for (String dirname : dirnames) {
            File path = new File(basedir, dirname);
            if (!path.mkdirs()) {
                throw new IOException("Could not create directory: " + path);
            }
        }
    }

}
