package it.unimi.dsi.fastutil.objects;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2ShortSortedMap<K> extends Object2ShortMap<K>, SortedMap<K, Short> {
    @Override // java.util.SortedMap
    Object2ShortSortedMap<K> headMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2ShortMap, java.util.Map
    ObjectSortedSet<K> keySet();

    @Override // it.unimi.dsi.fastutil.objects.Object2ShortMap
    ObjectSortedSet<Object2ShortMap.Entry<K>> object2ShortEntrySet();

    @Override // java.util.SortedMap
    Object2ShortSortedMap<K> subMap(K k, K k2);

    @Override // java.util.SortedMap
    Object2ShortSortedMap<K> tailMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2ShortMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Short> values2();

    @Override // it.unimi.dsi.fastutil.objects.Object2ShortMap, java.util.Map
    @Deprecated
    default ObjectSortedSet<Map.Entry<K, Short>> entrySet() {
        return object2ShortEntrySet();
    }
}
