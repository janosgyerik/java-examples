package com.janosgyerik.examples.util;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestUtils {

    private static final String TAG = TestUtils.class.getSimpleName();

    public static File createTempFile() throws IOException {
        return File.createTempFile(TAG, Long.toString(System.nanoTime()));
    }

    public static File createTempDir() throws IOException {
        File file = createTempFile();
        if (!file.delete()) {
            throw new IOException("Could not delete temp file: " + file.getAbsolutePath());
        }
        if (!file.mkdir()) {
            throw new IOException("Could not create temp dir: " + file.getAbsolutePath());
        }
        return file;
    }

    public static void setupCleanDir(File path) throws IOException {
        deleteDirRecursively(path);
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

    public static void deleteDirRecursively(File path) throws IOException {
        String[] items = path.list();
        if (items != null) {
            for (String item : items) {
                File file = new File(path, item);
                if (file.isFile()) {
                    if (!file.delete()) {
                        throw new IOException("Could not delete file: " + file);
                    }
                } else if (file.isDirectory()) {
                    deleteDirRecursively(file);
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
