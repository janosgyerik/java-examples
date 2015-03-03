package com.janosgyerik.examples.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FrequencyCounterTest {
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
}
