package com.janosgyerik.tools_wip;

import java.util.HashMap;
import java.util.Map;

class HashBiMap<K, V> extends AbstractBiMap<K, V> {
    @Override
    protected Map<K, V> createKeyToValueMap() {
        return new HashMap<>();
    }

    @Override
    protected Map<V, K> createValueToKeyMap() {
        return new HashMap<>();
    }
}
