package com.janosgyerik.examples.multicolumn;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegexColumnSplitterTest {
    @Test
    public void testSplitLine_OneCol() throws Exception {
        assertEquals("[hello]", ColumnSplitters.simpleCsvSplitter().split("hello").toString());
    }

    @Test
    public void testSplitLine_TwoCols() throws Exception {
        assertEquals("[hello, there]", ColumnSplitters.simpleCsvSplitter().split("hello,there").toString());
    }

    @Test
    public void testSplitLine_TwoCols_With_LeadingWhitespace() throws Exception {
        assertEquals("[hello, there]", ColumnSplitters.simpleCsvSplitter().split("  hello,there").toString());
    }

    @Test
    public void testSplitLine_TwoCols_With_TrailingWhitespace() throws Exception {
        assertEquals("[hello, there]", ColumnSplitters.simpleCsvSplitter().split("hello,there  ").toString());
    }

    @Test
    public void testSplitLine_TwoCols_With_WhitespaceBetweenCols() throws Exception {
        assertEquals("[hello, there]", ColumnSplitters.simpleCsvSplitter().split("hello   ,   there").toString());
    }
}
