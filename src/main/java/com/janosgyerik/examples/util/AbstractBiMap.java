package com.janosgyerik.examples.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class AbstractBiMap<K, V> implements BiMap<K, V> {

    Map<K, V> keyToValue = createKeyToValueMap();
    Map<V, K> valueToKey = createValueToKeyMap();

    protected abstract Map<K, V> createKeyToValueMap();

    protected abstract Map<V, K> createValueToKeyMap();

    @Override
    public K getKeyByValue(V value) {
        return valueToKey.get(value);
    }

    @Override
    public int size() {
        return keyToValue.size();
    }

    @Override
    public boolean isEmpty() {
        return keyToValue.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keyToValue.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return valueToKey.containsKey(value);
    }

    @Override
    public V get(Object key) {
        return keyToValue.get(key);
    }

    @Override
    public V put(K key, V value) {
        if (keyToValue.containsKey(key) || valueToKey.containsKey(value)) {
            throw new UnsupportedOperationException("Updating the bimap with an existing key or value is not supported yet.");
        }
        keyToValue.put(key, value);
        valueToKey.put(value, key);
        return value;
    }

    @Override
    public V remove(Object key) {
        valueToKey.remove(keyToValue.get(key));
        return keyToValue.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            put(key, value);
        }
    }

    @Override
    public void clear() {
        keyToValue.clear();
        valueToKey.clear();
    }

    @Override
    public Set<K> keySet() {
        return keyToValue.keySet();
    }

    @Override
    public Collection<V> values() {
        return valueToKey.keySet();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return keyToValue.entrySet();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return keyToValue.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        keyToValue.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public V putIfAbsent(K key, V value) {
        V v = keyToValue.get(key);
        if (v == null) {
            v = keyToValue.put(key, value);
        }
        return v;
    }

    @Override
    public boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public V replace(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
