package com.janosgyerik.tools_wip;

import org.junit.Test;

import static com.janosgyerik.tools_wip.StringUtils.replace;
import static org.junit.Assert.assertEquals;

public class StringUtilsTest {
    @Test
    public void test_empty_text() {
        assertEquals("", replace("", new String[]{"foo"}, new String[]{"bar"}));
    }

    @Test
    public void test_empty_patterns_and_replacements() {
        assertEquals("", replace("", new String[0], new String[0]));
    }

    @Test
    public void test_replace_one_pattern_once() {
        assertEquals("bar", replace("foo", new String[]{"foo"}, new String[]{"bar"}));
    }

    @Test
    public void test_replace_one_pattern_twice() {
        assertEquals("barbar", replace("foofoo", new String[]{"foo"}, new String[]{"bar"}));
    }

    @Test
    public void test_replace_two_patterns_simultaneously() {
        assertEquals("barbaz", replace("foobar",
                new String[]{"foo", "bar"},
                new String[]{"bar", "baz"}));
    }

    //TODO invalid input, should throw exception
    //@Test
    public void test_ambiguous_patterns_should_throw() {
        assertEquals("barbar", replace("foobar",
                new String[]{"foo", "foo"},
                new String[]{"bar", "baz"}));
    }

    @Test
    public void test_no_matches() {
        assertEquals("foofoo", replace("foofoo", new String[]{"x"}, new String[]{"bar"}));
    }

    @Test
    public void test_replace_multiple_simultaneous_patterns() {
        assertEquals("Once upon a foo, there was a bar and a baz, and another bar and a cat.",
                replace("Once upon a baz, there was a foo and a bar, and another foo and a cat.",
                        new String[]{"foo", "bar", "baz"},
                        new String[]{"bar", "baz", "foo"})
        );
    }
}
