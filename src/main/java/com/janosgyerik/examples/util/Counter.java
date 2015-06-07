package com.janosgyerik.examples.util;

import java.util.*;

public class Counter<T> {

    private final Map<T, Integer> counts = new HashMap<>();

    private final Comparator<T> countComparator = (o1, o2) -> Integer.compare(counts.get(o1),
            counts.get(o2));

    public void add(T item) {
        Integer count = counts.get(item);
        if (count == null) {
            count = 0;
        }
        counts.put(item, count + 1);
    }

    public void addAll(Collection<T> items) {
        items.forEach(this::add);
    }

    public T getMostFrequentItem() {
        return toSortedMap().lastKey();
    }

    public int getMostFrequentCount() {
        SortedMap<T, Integer> sortedMap = toSortedMap();
        T lastKey = sortedMap.lastKey();
        return sortedMap.get(lastKey);
    }

    private SortedMap<T, Integer> toSortedMap(Comparator<T> comparator) {
        SortedMap<T, Integer> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(counts);
        return sortedMap;
    }

    public SortedMap<T, Integer> toSortedMap() {
        return toSortedMap(countComparator);
    }

    public SortedMap<T, Integer> toReversedMap() {
        return toSortedMap(Collections.reverseOrder(countComparator));
    }

    private List<T> toSortedList(Comparator<T> comparator) {
        List<T> list = new ArrayList<>(counts.keySet());
        Collections.sort(list, comparator);
        return list;
    }

    public List<T> toSortedList() {
        return toSortedList(countComparator);
    }

    public List<T> toReversedList() {
        return toSortedList(Collections.reverseOrder(countComparator));
    }
}
