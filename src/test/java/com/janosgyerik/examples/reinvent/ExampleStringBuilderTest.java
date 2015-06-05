package com.janosgyerik.examples.reinvent;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExampleStringBuilderTest {
    @Test
    public void testAppendString() {
        String sentence = "hello there world";
        ExampleStringBuilder builder = new ExampleStringBuilder();
        for (String word : sentence.split(" ")) {
            builder.append(word).append(" ");
        }
        assertEquals(sentence + " ", builder.toString());
    }
}
