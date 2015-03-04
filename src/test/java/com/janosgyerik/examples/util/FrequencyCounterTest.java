package com.janosgyerik.examples.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FrequencyCounterTest {
    @Test
    public void test_getMostFrequentItem() {
        FrequencyCounter<Integer> counter = new FrequencyCounter<>();
        counter.addAll(Arrays.asList(1, 4, 9, 3, 4, 5, 4, 9));
        assertEquals(new Integer(4), counter.getMostFrequentItem());
    }

    @Test
    public void test_getMostFrequentCount() {
        FrequencyCounter<Integer> counter = new FrequencyCounter<>();
        counter.addAll(Arrays.asList(1, 4, 9, 3, 4, 5, 4, 9));
        assertEquals(3, counter.getMostFrequentCount());
    }

    @Test
    public void test_getMostFrequentLetter() {
        FrequencyCounter<Character> counter = new FrequencyCounter<>();
        String text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla " +
                "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
                "culpa qui officia deserunt mollit anim id est laborum.";

        for (char c : text.replace(" ", "").toCharArray()) {
            counter.add(c);
        }
        assertEquals(new Character('i'), counter.getMostFrequentItem());
    }

    @Test
    public void test_map_hi_hi_hello() {
        FrequencyCounter<String> counter = new FrequencyCounter<>();
        counter.add("hi");
        counter.add("hi");
        counter.add("hello");
        assertEquals("{hello=1, hi=2}", counter.toSortedMap().toString());
    }

    @Test
    public void test_map_hello_hi_hi() {
        FrequencyCounter<String> counter = new FrequencyCounter<>();
        counter.add("hello");
        counter.add("hi");
        counter.add("hi");
        assertEquals("{hello=1, hi=2}", counter.toSortedMap().toString());
    }

    @Test
    public void test_map_hello_hi_hi_hello_hello() {
        FrequencyCounter<String> counter = new FrequencyCounter<>();
        counter.add("hello");
        counter.add("hi");
        counter.add("hi");
        counter.add("hello");
        counter.add("hello");
        assertEquals("{hi=2, hello=3}", counter.toSortedMap().toString());
    }

    @Test
    public void test_sortedList_hi_hi_hello() {
        FrequencyCounter<String> counter = new FrequencyCounter<>();
        counter.add("hi");
        counter.add("hi");
        counter.add("hello");
        assertEquals("[hello, hi]", counter.toSortedList().toString());
    }

    @Test
    public void test_sortedList_hello_hi_hi() {
        FrequencyCounter<String> counter = new FrequencyCounter<>();
        counter.add("hello");
        counter.add("hi");
        counter.add("hi");
        assertEquals("[hello, hi]", counter.toSortedList().toString());
    }

    @Test
    public void test_sortedList_hello_hi_hi_hello_hello() {
        FrequencyCounter<String> counter = new FrequencyCounter<>();
        counter.add("hello");
        counter.add("hi");
        counter.add("hi");
        counter.add("hello");
        counter.add("hello");
        assertEquals("[hi, hello]", counter.toSortedList().toString());
    }

    @Test
    public void test_reversedList_hi_hi_hello() {
        FrequencyCounter<String> counter = new FrequencyCounter<>();
        counter.add("hi");
        counter.add("hi");
        counter.add("hello");
        assertEquals("[hi, hello]", counter.toReversedList().toString());
    }

    @Test
    public void test_reversedList_hello_hi_hi() {
        FrequencyCounter<String> counter = new FrequencyCounter<>();
        counter.add("hello");
        counter.add("hi");
        counter.add("hi");
        assertEquals("[hi, hello]", counter.toReversedList().toString());
    }

    @Test
    public void test_reversedList_hello_hi_hi_hello_hello() {
        FrequencyCounter<String> counter = new FrequencyCounter<>();
        counter.add("hello");
        counter.add("hi");
        counter.add("hi");
        counter.add("hello");
        counter.add("hello");
        assertEquals("[hello, hi]", counter.toReversedList().toString());
    }

    @Test
    public void test_reversedList_hello_hi_hi_hello_hello_null() {
        FrequencyCounter<String> counter = new FrequencyCounter<>();
        counter.add("hello");
        counter.add("hi");
        counter.add("hi");
        counter.add("hello");
        counter.add("hello");
        counter.add(null);
        assertEquals("[hello, hi, null]", counter.toReversedList().toString());
    }
}
