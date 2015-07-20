package com.janosgyerik.tools_wip;

import java.util.Map;

public interface BiMap<K, V> extends Map<K, V> {

    K getKeyByValue(V value);

}
