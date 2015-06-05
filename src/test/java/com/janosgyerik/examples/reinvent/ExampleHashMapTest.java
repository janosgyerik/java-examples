package com.janosgyerik.examples.reinvent;

import com.janosgyerik.examples.reinvent.ExampleHashMap;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ExampleHashMapTest {

    @Test
    public void testSize_empty() {
        Map<Integer, String> map = new ExampleHashMap<>();
        assertEquals(0, map.size());
    }

    @Test
    public void testSize_single() {
        Map<Integer, String> map = new ExampleHashMap<>();
        map.put(3, "Jack");
        assertEquals(1, map.size());
    }

    @Test
    public void testSize_after_removal() {
        Map<Integer, String> map = new ExampleHashMap<>();
        map.put(3, "Jack");
        assertEquals(1, map.size());
        map.remove(3);
        assertEquals(0, map.size());
    }

    @Test
    public void testIsEmpty_init() {
        Map<Integer, String> map = new ExampleHashMap<>();
        assertTrue(map.isEmpty());
    }

    @Test
    public void testIsEmpty_after_put() {
        Map<Integer, String> map = new ExampleHashMap<>();
        map.put(3, "Jack");
        assertFalse(map.isEmpty());
    }

    @Test
    public void testIsEmpty_after_remove() {
        Map<Integer, String> map = new ExampleHashMap<>();
        map.put(3, "Jack");
        assertFalse(map.isEmpty());
        map.remove(3);
        assertTrue(map.isEmpty());
    }

    @Test
    public void testPut_degenerate() {
        Map<Integer, String> map = new ExampleHashMap<>();
        int size = 100;
        for (int i = 0; i < size; ++i) {
            map.put(i, "Jack-" + i);
        }
        assertEquals(size, map.size());
    }

    @Test
    public void testContainsKey() {

    }

    @Test
    public void testContainsValue() {

    }

    @Test
    public void testGet() {
        Map<Integer, String> map = new ExampleHashMap<>();
        assertNull(map.get(3));
        map.put(3, "Jack");
        assertEquals("Jack", map.get(3));
        map.remove(3);
        assertNull(map.get(3));
    }

    @Test
    public void testGet_degenerate() {
        Map<Integer, String> map = new ExampleHashMap<>();
        int size = 100;
        for (int i = 0; i < size; ++i) {
            map.put(i, "Jack-" + i);
        }
        assertEquals(size, map.size());

        for (int i = 0; i < size; ++i) {
            assertEquals("Jack-" + i, map.get(i));
        }
    }

    @Test
    public void testPut() {

    }

    @Test
    public void testRemove() {

    }

    @Test
    public void testPutAll() {

    }

    @Test
    public void testClear() {

    }

    @Test
    public void testKeySet() {

    }

    @Test
    public void testValues() {

    }

    @Test
    public void testEntrySet() {

    }
}