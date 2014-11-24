package com.janosgyerik.examples.misc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class FlatIteratorTest {

    @Test
    public void testEmpty() {
        FlatIterator<Object> iter = new FlatIterator<>(Arrays.asList());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testSingletonList() {
        FlatIterator<Integer> iter = new FlatIterator<>(Arrays.asList(3));
        assertTrue(iter.hasNext());
        iter.next();
        assertFalse(iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testAccessBeyond() {
        new FlatIterator<>(Arrays.asList()).next();
    }

    @Test
    public void testAdvanceWithoutHasNext() {
        Integer num = 3;
        List<Integer> list = Arrays.asList(num);
        FlatIterator<Integer> iter = new FlatIterator<>(list);
        assertEquals(num, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNullElements() {
        Integer num = 3;
        List<Integer> listWithNull = Arrays.asList(num, null);
        FlatIterator<Integer> iter = new FlatIterator<>(listWithNull);
        assertEquals(num, iter.next());
        assertNull(iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testIteratorCount() {
        List<Integer> list = Arrays.asList(3, null, 4, 5);
        FlatIterator<Integer> iter = new FlatIterator<>(list);
        int count = 0;
        while (iter.hasNext()) {
            ++count;
            iter.next();
        }
        assertEquals(list.size(), count);
    }

    @Test
    public void testMixedElements() {
        List<Object> mixed = new ArrayList<>();
        mixed.add(3);
        mixed.add("hello");
        mixed.add(null);
        FlatIterator<Object> iter = new FlatIterator<>(mixed);
        iter.next();
        iter.next();
        iter.next();
        assertFalse(iter.hasNext());
    }

    @Test
    public void testDeeplyNestedList() {
        List<Object> nested = Arrays.asList(1, 2, Arrays.asList(3, 4), Arrays.asList(5, Arrays.asList(6, 7, 8, Arrays.asList(Arrays.asList(9), 10))), Arrays.asList(Arrays.asList(), Arrays.asList(), Arrays.asList(11)), Arrays.asList(Arrays.asList(), Arrays.asList(), Arrays.asList()), Arrays.asList(Arrays.asList(Arrays.asList())), 12, Arrays.asList(), Arrays.asList(13, 14));
        FlatIterator<Object> iter = new FlatIterator<>(nested);
        for (int i = 1; i <= 14; ++i) {
            assertTrue(iter.hasNext());
            assertEquals(i, iter.next());
        }
        assertFalse(iter.hasNext());
    }
}