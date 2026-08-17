package org.jetbrains.kotlin.js.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class Maps {
    private Maps() {
    }

    public static <K, V> Map<K, V> put(Map<K, V> map, K k, V v) {
        int size = map.size();
        if (size == 0) {
            return Collections.singletonMap(k, v);
        }
        if (size != 1) {
            map.put(k, v);
            return map;
        }
        if (map.containsKey(k)) {
            return Collections.singletonMap(k, v);
        }
        HashMap map2 = new HashMap();
        map2.put(map.keySet().iterator().next(), map.values().iterator().next());
        map2.put(k, v);
        return map2;
    }
}
