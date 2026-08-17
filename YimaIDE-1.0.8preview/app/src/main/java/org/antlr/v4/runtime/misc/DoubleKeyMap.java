package org.antlr.v4.runtime.misc;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class DoubleKeyMap<Key1, Key2, Value> {
    Map<Key1, Map<Key2, Value>> data = new LinkedHashMap();

    public Value get(Key1 key1, Key2 key2) {
        Map<Key2, Value> map = this.data.get(key1);
        if (map == null) {
            return null;
        }
        return map.get(key2);
    }

    public Set<Key2> keySet(Key1 key1) {
        Map<Key2, Value> map = this.data.get(key1);
        if (map == null) {
            return null;
        }
        return map.keySet();
    }

    public Value put(Key1 key1, Key2 key2, Value value) {
        Value value2;
        Map<Key2, Value> linkedHashMap = this.data.get(key1);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            this.data.put(key1, linkedHashMap);
            value2 = null;
        } else {
            value2 = linkedHashMap.get(key2);
        }
        linkedHashMap.put(key2, value);
        return value2;
    }

    public Collection<Value> values(Key1 key1) {
        Map<Key2, Value> map = this.data.get(key1);
        if (map == null) {
            return null;
        }
        return map.values();
    }

    public Map<Key2, Value> get(Key1 key1) {
        return this.data.get(key1);
    }

    public Set<Key1> keySet() {
        return this.data.keySet();
    }
}
