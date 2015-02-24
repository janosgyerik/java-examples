package com.janosgyerik.examples.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MinHeapTest {

    private String toString(int[] arr) {
        return Arrays.toString(MinHeap.fromArray(arr).toArray());
    }

    @Test
    public void testFromArray_3_2_1() {
        assertEquals("[1, 3, 2]", toString(new int[]{3, 2, 1}));
    }

    @Test
    public void testFromArray_100_19_36_17_3_2_7_25_1() {
        assertEquals("[1, 2, 3, 17, 19, 36, 7, 100, 25]", toString(new int[]{100, 19, 36, 17, 3, 2, 7, 25, 1}));
    }
}