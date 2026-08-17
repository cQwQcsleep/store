package org.jetbrains.kotlin.util.slicedMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface MapSupplier {
    public static final MapSupplier HASH_MAP_SUPPLIER = new MapSupplier() { // from class: org.jetbrains.kotlin.util.slicedMap.MapSupplier.1
        @Override // org.jetbrains.kotlin.util.slicedMap.MapSupplier
        public <K, V> Map<K, V> get() {
            return new HashMap();
        }
    };
    public static final MapSupplier LINKED_HASH_MAP_SUPPLIER = new MapSupplier() { // from class: org.jetbrains.kotlin.util.slicedMap.MapSupplier.2
        @Override // org.jetbrains.kotlin.util.slicedMap.MapSupplier
        public <K, V> Map<K, V> get() {
            return new LinkedHashMap();
        }
    };

    <K, V> Map<K, V> get();
}
