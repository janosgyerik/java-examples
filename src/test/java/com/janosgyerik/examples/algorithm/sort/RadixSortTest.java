package com.janosgyerik.examples.algorithm.sort;

public class RadixSortTest extends SortTest {
    @Override
    void sort(int[] arr) {
        RadixSort.sort(arr);
    }
}