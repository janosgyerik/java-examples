package com.janosgyerik.tools_wip;

import com.janosgyerik.tools_wip.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {
    @Test
    public void testSingleExact() {
        assertEquals("bar", StringUtils.replaceEach("foo", new String[]{"foo"}, new String[]{"bar"}));
    }

    @Test
    public void testReplaceTwice() {
        assertEquals("barbar", StringUtils.replaceEach("foofoo", new String[]{"foo"}, new String[]{"bar"}));
    }

    @Test
    public void testReplaceTwoPatterns() {
        assertEquals("barbaz", StringUtils.replaceEach("foobar",
                new String[]{"foo", "bar"},
                new String[]{"bar", "baz"}));
    }

    //TODO invalid input, should throw exception
    //@Test
    public void testDuplicatePatterns() {
        assertEquals("barbar", StringUtils.replaceEach("foobar",
                new String[]{"foo", "foo"},
                new String[]{"bar", "baz"}));
    }

    @Test
    public void testReplaceNone() {
        assertEquals("foofoo", StringUtils.replaceEach("foofoo", new String[]{"x"}, new String[]{"bar"}));
    }

    @Test
    public void testStory() {
        assertEquals("Once upon a foo, there was a bar and a baz, and another bar and a cat.",
                StringUtils.replaceEach("Once upon a baz, there was a foo and a bar, and another foo and a cat.",
                        new String[]{"foo", "bar", "baz"},
                        new String[]{"bar", "baz", "foo"})
        );
    }
}
