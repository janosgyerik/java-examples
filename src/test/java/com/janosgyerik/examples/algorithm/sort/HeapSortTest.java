package com.janosgyerik.examples.algorithm.sort;

public class HeapSortTest extends SortTest {
    @Override
    void sort(int[] arr) {
        HeapSort.sort(arr);
    }
}