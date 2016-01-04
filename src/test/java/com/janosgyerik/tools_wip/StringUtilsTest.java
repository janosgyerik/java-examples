package com.janosgyerik.tools_wip;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.janosgyerik.tools_wip.StringUtils.*;
import static org.junit.Assert.assertEquals;

public class StringUtilsTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

    @Test
    public void test_no_matches() {
        assertEquals("foofoo", replace("foofoo", new String[]{"x"}, new String[]{"bar"}));
    }

    @Test
    public void test_overlap() {
        assertEquals("foovel", replace("applevel", new String[]{"apple", "level"}, new String[]{"foo", "bar"}));
    }

    @Test
    public void test_replace_multiple_simultaneous_patterns() {
        assertEquals("Once upon a foo, there was a bar and a baz, and another bar and a cat.",
                replace("Once upon a baz, there was a foo and a bar, and another foo and a cat.",
                        new String[]{"foo", "bar", "baz"},
                        new String[]{"bar", "baz", "foo"})
        );
    }

    @Test
    public void test_null_text_throws() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_PARAM);

        replace(null, new String[0], new String[0]);
    }

    @Test
    public void test_null_patterns_throws() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_PARAM);

        replace("", null, new String[0]);
    }

    @Test
    public void test_null_replacements_throws() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_PARAM);

        replace("", new String[0], null);
    }

    @Test
    public void test_more_replacements_than_patterns_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_PATTERNS_REPLACEMENTS_LENGTH_MISMATCH);

        replace("", new String[0], new String[]{"bar"});
    }

    @Test
    public void test_more_patterns_than_replacements_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_PATTERNS_REPLACEMENTS_LENGTH_MISMATCH);

        replace("", new String[]{"foo"}, new String[0]);
    }

    @Test
    public void test_null_element_in_patterns_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_OR_EMPTY_PATTERN);

        replace("", new String[]{null}, new String[]{"bar"});
    }

    @Test
    public void test_empty_element_in_patterns_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_OR_EMPTY_PATTERN);

        replace("", new String[]{""}, new String[]{"bar"});
    }

    @Test
    public void test_null_element_in_replacements_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_REPLACEMENT);

        replace("", new String[]{"foo"}, new String[]{null});
    }

    @Test
    public void test_non_distinct_patterns_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_DUPLICATE_PATTERNS);

        replace("", new String[]{"foo", "foo"}, new String[]{"bar", "baz"});
    }
}
