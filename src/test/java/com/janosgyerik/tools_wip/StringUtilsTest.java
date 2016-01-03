package com.janosgyerik.tools_wip;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {
    @Test
    public void testSingleExact() {
        assertEquals("bar", StringUtils.replace("foo", new String[]{"foo"}, new String[]{"bar"}));
    }

    @Test
    public void testReplaceTwice() {
        assertEquals("barbar", StringUtils.replace("foofoo", new String[]{"foo"}, new String[]{"bar"}));
    }

    @Test
    public void testReplaceTwoPatterns() {
        assertEquals("barbaz", StringUtils.replace("foobar",
                new String[]{"foo", "bar"},
                new String[]{"bar", "baz"}));
    }

    //TODO invalid input, should throw exception
    //@Test
    public void testDuplicatePatterns() {
        assertEquals("barbar", StringUtils.replace("foobar",
                new String[]{"foo", "foo"},
                new String[]{"bar", "baz"}));
    }

    @Test
    public void testReplaceNone() {
        assertEquals("foofoo", StringUtils.replace("foofoo", new String[]{"x"}, new String[]{"bar"}));
    }

    @Test
    public void testStory() {
        assertEquals("Once upon a foo, there was a bar and a baz, and another bar and a cat.",
                StringUtils.replace("Once upon a baz, there was a foo and a bar, and another foo and a cat.",
                        new String[]{"foo", "bar", "baz"},
                        new String[]{"bar", "baz", "foo"})
        );
    }
}
