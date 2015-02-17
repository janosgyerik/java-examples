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

    public static void deleteRecursively(File fileOrDir) throws IOException {
        String[] names = fileOrDir.list();
        if (names != null) {
            for (String name : names) {
                File file = new File(fileOrDir, name);
                if (file.isFile()) {
                    deleteFile(file);
                } else if (file.isDirectory()) {
                    deleteRecursively(file);
                } else {
                    throw new IOException("Neither file nor dir: " + file);
                }
            }
            if (!fileOrDir.delete()) {
                throw new IOException("Could not delete dir: " + fileOrDir);
            }
        } else if (fileOrDir.isFile()) {
            deleteFile(fileOrDir);
        }
    }

    public static void deleteFile(File file) throws IOException {
        if (!file.delete()) {
            throw new IOException("Could not delete file: " + file);
        }
    }
}
