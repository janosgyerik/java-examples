package com.janosgyerik.examples.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringBuilderTest {
    @Test
    public void testAppendString() {
        String sentence = "hello there world";
        StringBuilder builder = new StringBuilder();
        for (String word : sentence.split(" ")) {
            builder.append(word).append(" ");
        }
        assertEquals(sentence + " ", builder.toString());
    }
}
