package com.janosgyerik.examples.algorithm.sort;

public class RadixSortWithBitShiftingTest extends SortTest {
    @Override
    void sort(int[] arr) {
        RadixSortWithBitShifting.sort(arr);
    }
}