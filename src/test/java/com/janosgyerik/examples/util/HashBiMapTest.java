package com.janosgyerik.examples.util;

import org.junit.Test;

import java.util.TreeSet;

import static org.junit.Assert.*;

public class HashBiMapTest {
    @Test
    public void testPut() {
        HashBiMap<String, Integer> bimap = new HashBiMap<>();
        bimap.put("hello", 11);
        bimap.put("bye", 15);
        bimap.put("world", 12);

        assertEquals(3, bimap.keySet().size());
        assertEquals(3, bimap.values().size());

        assertEquals("[bye, hello, world]", new TreeSet<String>(bimap.keySet()).toString());
        assertEquals("[11, 12, 15]", new TreeSet<Integer>(bimap.values()).toString());
    }

    @Test
    public void testRemove() {
        HashBiMap<String, Integer> bimap = new HashBiMap<>();
        bimap.put("hello", 11);
        bimap.put("bye", 15);
        bimap.put("world", 12);

        bimap.remove("hello");

        assertEquals(2, bimap.keySet().size());
        assertEquals(2, bimap.values().size());

        assertEquals("[bye, world]", new TreeSet<String>(bimap.keySet()).toString());
        assertEquals("[12, 15]", new TreeSet<Integer>(bimap.values()).toString());
    }

    @Test
    public void testContainsValue() {
        HashBiMap<String, Integer> bimap = new HashBiMap<>();
        bimap.put("hello", 11);
        bimap.put("bye", 15);
        bimap.put("world", 12);

        assertTrue(bimap.containsValue(11));
        assertTrue(bimap.containsValue(12));
        assertFalse(bimap.containsValue(121));
    }

    @Test
    public void testKeyByValue() {
        HashBiMap<String, Integer> bimap = new HashBiMap<>();
        bimap.put("hello", 11);
        bimap.put("bye", 15);
        bimap.put("world", 12);

        assertEquals("hello", bimap.getKeyByValue(11));
        assertEquals("world", bimap.getKeyByValue(12));
        assertNull(bimap.getKeyByValue(121));
    }
}
