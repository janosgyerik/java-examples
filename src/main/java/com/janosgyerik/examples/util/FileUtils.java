package com.janosgyerik.examples.util;

import java.io.*;

public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    private FileUtils() {
        // utility class, forbidden constructor
    }

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

    // http://grepcode.com/file/repo1.maven.org/maven2/commons-io/commons-io/2.4/org/apache/commons/io/FileUtils.java
    public static boolean contentEquals(File file1, File file2) throws IOException {
        boolean file1Exists = file1.exists();
        if (file1Exists != file2.exists()) {
            return false;
        }

        if (!file1Exists) {
            // two not existing files are equal
            return true;
        }

        if (file1.isDirectory() || file2.isDirectory()) {
            // don't want to compare directory contents
            throw new IOException("Can't compare directories, only files");
        }

        if (file1.length() != file2.length()) {
            // lengths differ, cannot be equal
            return false;
        }

        if (file1.getCanonicalFile().equals(file2.getCanonicalFile())) {
            // same file
            return true;
        }

        InputStream input1 = null;
        InputStream input2 = null;
        try {
            input1 = new FileInputStream(file1);
            input2 = new FileInputStream(file2);
            return IOUtils.contentEquals(input1, input2);

        } finally {
            IOUtils.closeQuietly(input1);
            IOUtils.closeQuietly(input2);
        }
    }
}
