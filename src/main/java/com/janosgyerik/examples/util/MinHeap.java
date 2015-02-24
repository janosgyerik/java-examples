package com.janosgyerik.examples.util;

public class MinHeap {

    private int[] storage;
    private int size;

    public MinHeap(int capacity) {
        storage = new int[capacity];
        size = 0;
    }

    public static MinHeap fromArray(int[] arr) {
        MinHeap heap = new MinHeap(arr.length);
        for (int num : arr) {
            heap.insert(num);
        }
        return heap;
    }

    public int[] toArray() {
        int[] copy = new int[size];
        System.arraycopy(storage, 0, copy, 0, size);
        return copy;
    }

    private void insert(int num) {
        ensureCapacity(size + 1);
        storage[size++] = num;

        int currentIndex = size - 1;
        while (currentIndex > 0) {
            int current = storage[currentIndex];
            int parentIndex = getParentIndex(currentIndex);
            int parent = storage[parentIndex];
            if (current < parent) {
                storage[parentIndex] = current;
                storage[currentIndex] = parent;
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    private int getParentIndex(int index) {
        return (index - 2 + index % 2) / 2;
    }

    private void ensureCapacity(int targetSize) {
        if (targetSize >= storage.length) {
            int[] newStorage = new int[storage.length * 2];
            System.arraycopy(storage, 0, newStorage, 0, size);
            storage = newStorage;
        }
    }

}
