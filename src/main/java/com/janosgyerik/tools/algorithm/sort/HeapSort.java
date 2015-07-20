package com.janosgyerik.tools.algorithm.sort;

import com.janosgyerik.examples.reinvent.MinHeap;

public class HeapSort {
    public static void sort(int[] arr) {
        MinHeap minHeap = MinHeap.fromArray(arr);
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = minHeap.removeTop();
        }
    }
}
