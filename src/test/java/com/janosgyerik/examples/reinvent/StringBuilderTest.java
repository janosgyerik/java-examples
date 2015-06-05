package com.janosgyerik.examples.reinvent;

import com.janosgyerik.examples.reinvent.StringBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringBuilderTest {
    @Test
    public void testAppendString() {
        String sentence = "hello there world";
        com.janosgyerik.examples.reinvent.StringBuilder builder = new StringBuilder();
        for (String word : sentence.split(" ")) {
            builder.append(word).append(" ");
        }
        assertEquals(sentence + " ", builder.toString());
    }
}
