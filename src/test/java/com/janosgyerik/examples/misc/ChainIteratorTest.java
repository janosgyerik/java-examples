package com.janosgyerik.examples.misc;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ChainIteratorTest {
    @Test
    public void testSingle() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        ChainIterator<Integer> iter2x = new ChainIterator<>(list1.iterator());
        for (Integer item : list1) {
            assertTrue(iter2x.hasNext());
            assertEquals(item, iter2x.next());
        }
        assertFalse(iter2x.hasNext());
    }

    @Test
    public void testConcat() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);
        ChainIterator<Integer> iter2x = new ChainIterator<>(list1.iterator(), list2.iterator());
        for (Integer item : list1) {
            assertTrue(iter2x.hasNext());
            assertEquals(item, iter2x.next());
        }
        for (Integer item : list2) {
            assertTrue(iter2x.hasNext());
            assertEquals(item, iter2x.next());
        }
        assertFalse(iter2x.hasNext());
    }

    @Test
    public void testConcatWithEmptyInMiddle() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);
        ChainIterator<Integer> iter2x = new ChainIterator<>(list1.iterator(), Collections.<Integer>emptyList().iterator(), list2.iterator());
        for (Integer item : list1) {
            assertTrue(iter2x.hasNext());
            assertEquals(item, iter2x.next());
        }
        for (Integer item : list2) {
            assertTrue(iter2x.hasNext());
            assertEquals(item, iter2x.next());
        }
        assertFalse(iter2x.hasNext());
    }

    @Test
    public void testConcatWithManyEmpty() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);
        ChainIterator<Integer> iter2x = new ChainIterator<>(
                Collections.<Integer>emptyList().iterator(),
                list1.iterator(),
                Collections.<Integer>emptyList().iterator(),
                Collections.<Integer>emptyList().iterator(),
                list2.iterator());
        for (Integer item : list1) {
            assertTrue(iter2x.hasNext());
            assertEquals(item, iter2x.next());
        }
        for (Integer item : list2) {
            assertTrue(iter2x.hasNext());
            assertEquals(item, iter2x.next());
        }
        assertFalse(iter2x.hasNext());
    }
}
