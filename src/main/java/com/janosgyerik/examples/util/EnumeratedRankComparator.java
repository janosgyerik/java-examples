package com.janosgyerik.examples.util;

import java.util.*;

public class EnumeratedRankComparator<T> implements Comparator<T> {
    private final Map<T, Integer> itemsToIndex;

    private EnumeratedRankComparator(List<T> items) {
        itemsToIndex = new HashMap<>(items.size());

        int index = 0;
        for (T item : items) {
            if (itemsToIndex.containsKey(item)) {
                throw new IllegalArgumentException("Inconsistent ranks: there should be no duplicates");
            }
            itemsToIndex.put(item, ++index);
        }
    }

    public static <T> Comparator<T> fromLowToHigh(List<T> items) {
        return Comparator.nullsFirst(new EnumeratedRankComparator<>(items));
    }

    public static <T> Comparator<T> fromHighToLow(List<T> items) {
        List<T> copy = new ArrayList<>(items);
        Collections.reverse(copy);
        return fromLowToHigh(copy);
    }

    @Override
    public int compare(T o1, T o2) {
        return itemsToIndex.get(o1).compareTo(itemsToIndex.get(o2));
    }
}
