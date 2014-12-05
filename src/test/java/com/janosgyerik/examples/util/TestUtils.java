package com.janosgyerik.examples.util;

import java.io.File;
import java.io.IOException;

public class TestUtils {

    public static void setupCleanDir(File path) throws IOException {
        wipeDirRecursively(path);
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

    public static void wipeDirRecursively(File path) throws IOException {
        String[] items = path.list();
        if (items != null) {
            for (String item : items) {
                File file = new File(path, item);
                if (file.isFile()) {
                    if (!file.delete()) {
                        throw new IOException("Could not delete file: " + file);
                    }
                } else if (file.isDirectory()) {
                    wipeDirRecursively(file);
                }
            }
        }
        if (path.isDirectory()) {
            if (!path.delete()) {
                throw new IOException("Could not delete dir: " + path);
            }
        }
    }
}
