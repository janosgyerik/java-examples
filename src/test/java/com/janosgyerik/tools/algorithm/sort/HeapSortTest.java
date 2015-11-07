package com.janosgyerik.tools.algorithm.sort;

import org.junit.Ignore;

@Ignore
public class HeapSortTest extends SortTest {
    @Override
    void sort(int[] arr) {
        HeapSort.sort(arr);
    }
}