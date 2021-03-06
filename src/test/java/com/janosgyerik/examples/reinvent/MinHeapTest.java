package com.janosgyerik.examples.reinvent;

import com.janosgyerik.examples.reinvent.MinHeap;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    @Test
    public void testRemoveTop_1_2_3_x_1() {
        MinHeap heap = MinHeap.fromArray(new int[]{1, 2, 3});
        assertEquals(1, heap.removeTop());
        assertEquals("[2, 3]", heap.toString());
    }

    @Test
    public void testRemoveTop_1_2_3_x_1_2() {
        MinHeap heap = MinHeap.fromArray(new int[]{1, 2, 3});
        assertEquals(1, heap.removeTop());
        assertEquals(2, heap.removeTop());
        assertEquals("[3]", heap.toString());
    }

    @Test
    public void testRemoveTop_3_2_1_x_1() {
        MinHeap heap = MinHeap.fromArray(new int[]{3, 2, 1});
        assertEquals(1, heap.removeTop());
        assertEquals("[2, 3]", heap.toString());
    }

    @Test
    public void testRemoveTop_3_2_1_x_1_2() {
        MinHeap heap = MinHeap.fromArray(new int[]{3, 2, 1});
        assertEquals(1, heap.removeTop());
        assertEquals(2, heap.removeTop());
        assertEquals("[3]", heap.toString());
    }

    @Test
    public void testCapacityIncrease1_x_2_3_4_5_6_7_8_9_10() {
        MinHeap heap = MinHeap.fromArray(new int[]{1});
        assertEquals("[1]", heap.toString());

        List<Integer> list = new LinkedList<>(Arrays.asList(1));
        for (int i = 2; i < 11; ++i) {
            heap.insert(i);
            list.add(i);
            assertEquals(list.toString(), heap.toString());
        }
    }
}