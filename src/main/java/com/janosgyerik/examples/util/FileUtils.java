package com.janosgyerik.examples.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

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

    public static void deleteRecursively(File path) throws IOException {
        String[] items = path.list();
        if (items != null) {
            for (String item : items) {
                File file = new File(path, item);
                if (file.isFile()) {
                    deleteFile(file);
                } else if (file.isDirectory()) {
                    deleteRecursively(file);
                }
            }
            if (!path.delete()) {
                throw new IOException("Could not delete dir: " + path);
            }
        } else if (path.isFile()) {
            deleteFile(path);
        }
    }

    public static void deleteFile(File file) throws IOException {
        if (!file.delete()) {
            throw new IOException("Could not delete file: " + file);
        }
    }
}
