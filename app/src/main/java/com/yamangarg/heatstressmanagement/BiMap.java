package com.yamangarg.heatstressmanagement;

import java.util.HashMap;

public class BiMap<K,V> {

    public HashMap<K,V> map = new HashMap<K, V>();
    public HashMap<V,K> inversedMap = new HashMap<V, K>();

    void put(K k, V v) {
        map.put(k, v);
        inversedMap.put(v, k);
    }

    V get(K k) {
        return map.get(k);
    }

    K getKey(V v) {
        return inversedMap.get(v);
    }

}
