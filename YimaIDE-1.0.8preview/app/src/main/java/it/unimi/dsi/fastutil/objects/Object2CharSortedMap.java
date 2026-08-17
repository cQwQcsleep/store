package it.unimi.dsi.fastutil.objects;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2CharSortedMap<K> extends Object2CharMap<K>, SortedMap<K, Character> {
    @Override // java.util.SortedMap
    Object2CharSortedMap<K> headMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2CharMap, java.util.Map
    ObjectSortedSet<K> keySet();

    @Override // it.unimi.dsi.fastutil.objects.Object2CharMap
    ObjectSortedSet<Object2CharMap.Entry<K>> object2CharEntrySet();

    @Override // java.util.SortedMap
    Object2CharSortedMap<K> subMap(K k, K k2);

    @Override // java.util.SortedMap
    Object2CharSortedMap<K> tailMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2CharMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Character> values2();

    @Override // it.unimi.dsi.fastutil.objects.Object2CharMap, java.util.Map
    @Deprecated
    default ObjectSortedSet<Map.Entry<K, Character>> entrySet() {
        return object2CharEntrySet();
    }
}
