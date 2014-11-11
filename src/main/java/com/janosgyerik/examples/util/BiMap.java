package com.janosgyerik.examples.util;

import java.util.Map;

public interface BiMap<K, V> extends Map<K, V> {

    K getKeyByValue(V value);

}
