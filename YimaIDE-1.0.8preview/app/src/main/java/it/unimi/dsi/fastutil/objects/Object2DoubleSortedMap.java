package it.unimi.dsi.fastutil.objects;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2DoubleSortedMap<K> extends Object2DoubleMap<K>, SortedMap<K, Double> {
    @Override // java.util.SortedMap
    Object2DoubleSortedMap<K> headMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2DoubleMap, java.util.Map
    ObjectSortedSet<K> keySet();

    @Override // it.unimi.dsi.fastutil.objects.Object2DoubleMap
    ObjectSortedSet<Object2DoubleMap.Entry<K>> object2DoubleEntrySet();

    @Override // java.util.SortedMap
    Object2DoubleSortedMap<K> subMap(K k, K k2);

    @Override // java.util.SortedMap
    Object2DoubleSortedMap<K> tailMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2DoubleMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Double> values2();

    @Override // it.unimi.dsi.fastutil.objects.Object2DoubleMap, java.util.Map
    @Deprecated
    default ObjectSortedSet<Map.Entry<K, Double>> entrySet() {
        return object2DoubleEntrySet();
    }
}
