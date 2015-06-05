package com.janosgyerik.examples.reinvent;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class ExampleHashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private final LinkedList<Entry<K, V>>[] storage;
    private int size = 0;

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    public ExampleHashMap() {
        storage = new LinkedList[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        int targetPos = key.hashCode() % storage.length;
        if (storage[targetPos] != null) {
            LinkedList<Entry<K, V>> entries = storage[targetPos];
            for (Entry<K, V> entry : entries) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int targetPos = key.hashCode() % storage.length;
        LinkedList<Entry<K, V>> entries;
        if (storage[targetPos] == null) {
            entries = new LinkedList<>();
            storage[targetPos] = entries;
        } else {
            entries = storage[targetPos];
            for (Entry<K, V> entry : entries) {
                if (entry.getKey().equals(key)) {
                    return entry.setValue(value);
                }
            }
        }
        entries.add(new Entry<>(key, value));
        ++size;
        return null;
    }

    @Override
    public V remove(Object key) {
        int targetPos = key.hashCode() % storage.length;
        if (storage[targetPos] != null) {
            LinkedList<Entry<K, V>> entries = storage[targetPos];
            for (Entry<K, V> entry : entries) {
                if (entry.getKey().equals(key)) {
                    entries.remove(entry);
                    --size;
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }
}
