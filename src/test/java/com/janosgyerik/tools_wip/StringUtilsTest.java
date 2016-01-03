package com.janosgyerik.tools_wip;

import org.junit.Test;

import static com.janosgyerik.tools_wip.StringUtils.replace;
import static org.junit.Assert.assertEquals;

public class StringUtilsTest {
    @Test
    public void testSingleExact() {
        assertEquals("bar", replace("foo", new String[]{"foo"}, new String[]{"bar"}));
    }

    @Test
    public void testReplaceTwice() {
        assertEquals("barbar", replace("foofoo", new String[]{"foo"}, new String[]{"bar"}));
    }

    @Test
    public void testReplaceTwoPatterns() {
        assertEquals("barbaz", replace("foobar",
                new String[]{"foo", "bar"},
                new String[]{"bar", "baz"}));
    }

    //TODO invalid input, should throw exception
    //@Test
    public void testDuplicatePatterns() {
        assertEquals("barbar", replace("foobar",
                new String[]{"foo", "foo"},
                new String[]{"bar", "baz"}));
    }

    @Test
    public void testReplaceNone() {
        assertEquals("foofoo", replace("foofoo", new String[]{"x"}, new String[]{"bar"}));
    }

    @Test
    public void testStory() {
        assertEquals("Once upon a foo, there was a bar and a baz, and another bar and a cat.",
                replace("Once upon a baz, there was a foo and a bar, and another foo and a cat.",
                        new String[]{"foo", "bar", "baz"},
                        new String[]{"bar", "baz", "foo"})
        );
    }
}
