package it.unimi.dsi.fastutil.objects;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2FloatSortedMap<K> extends Object2FloatMap<K>, SortedMap<K, Float> {
    @Override // java.util.SortedMap
    Object2FloatSortedMap<K> headMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2FloatMap, java.util.Map
    ObjectSortedSet<K> keySet();

    @Override // it.unimi.dsi.fastutil.objects.Object2FloatMap
    ObjectSortedSet<Object2FloatMap.Entry<K>> object2FloatEntrySet();

    @Override // java.util.SortedMap
    Object2FloatSortedMap<K> subMap(K k, K k2);

    @Override // java.util.SortedMap
    Object2FloatSortedMap<K> tailMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2FloatMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Float> values2();

    @Override // it.unimi.dsi.fastutil.objects.Object2FloatMap, java.util.Map
    @Deprecated
    default ObjectSortedSet<Map.Entry<K, Float>> entrySet() {
        return object2FloatEntrySet();
    }
}
