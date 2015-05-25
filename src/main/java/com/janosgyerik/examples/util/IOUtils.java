package com.janosgyerik.examples.util;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

    private static final int EOF = -1;

    private IOUtils() {
        // utility class, forbidden constructor
    }

    // http://grepcode.com/file/repo1.maven.org/maven2/commons-io/commons-io/2.4/org/apache/commons/io/IOUtils.java
    public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
        if (!(input1 instanceof BufferedInputStream)) {
            input1 = new BufferedInputStream(input1);
        }
        if (!(input2 instanceof BufferedInputStream)) {
            input2 = new BufferedInputStream(input2);
        }

        int ch = input1.read();
        while (EOF != ch) {
            int ch2 = input2.read();
            if (ch != ch2) {
                return false;
            }
            ch = input1.read();
        }

        int ch2 = input2.read();
        return ch2 == EOF;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
