package com.janosgyerik.examples.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    public static File createTempFile() throws IOException {
        File tempFile = File.createTempFile(TAG, Long.toString(System.nanoTime()));
        tempFile.deleteOnExit();
        return tempFile;
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

    public static void deleteFile(File file) throws IOException {
        if (!file.delete()) {
            throw new IOException("Could not delete file: " + file);
        }
    }

    public static void deleteRecursively(File fileOrDir) throws IOException {
        if (fileOrDir.isFile()) {
            deleteFile(fileOrDir);
        } else if (fileOrDir.isDirectory()) {
            for (String name : fileOrDir.list()) {
                deleteRecursively(new File(fileOrDir, name));
            }
            if (!fileOrDir.delete()) {
                throw new IOException("Could not delete dir: " + fileOrDir);
            }
        } else {
            throw new IOException("Neither file nor dir: " + fileOrDir);
        }
    }
}
