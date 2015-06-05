package com.janosgyerik.examples.reinvent;

import java.util.Arrays;

public class ExampleStringBuilder {

    private static final int INITIAL_SIZE = 16;

    private char[] buffer;
    private int count;

    public ExampleStringBuilder() {
        buffer = new char[INITIAL_SIZE];
        count = 0;
    }

    public ExampleStringBuilder(int initialSize) {
        buffer = new char[initialSize];
        count = 0;
    }

    public ExampleStringBuilder(String string) {
        this();
        this.append(string);
    }

    public ExampleStringBuilder append(String string) {
        if (string == null) {
            string = "null";
        }
        int len = string.length();
        ensureCapacity(count + len);
        string.getChars(0, len, buffer, count);
        count += len;
        return this;
    }

    public ExampleStringBuilder reverse() {
        for (int i = 0; i < count / 2; ++i) {
            int indexFromEnd = count - i - 1;
            char work = buffer[i];
            buffer[i] = buffer[indexFromEnd];
            buffer[indexFromEnd] = work;
        }
        return this;
    }

    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity > buffer.length) {
            expandCapacity(minimumCapacity);
        }
    }

    private void expandCapacity(int minimumCapacity) {
        int newCapacity = buffer.length * 2 + 2;
        buffer = Arrays.copyOf(buffer, newCapacity);
    }

    @Override
    public String toString() {
        return new String(buffer, 0, count);
    }
}
