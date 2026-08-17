package it.unimi.dsi.fastutil.objects;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2BooleanSortedMap<K> extends Object2BooleanMap<K>, SortedMap<K, Boolean> {
    @Override // java.util.SortedMap
    Object2BooleanSortedMap<K> headMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2BooleanMap, java.util.Map
    ObjectSortedSet<K> keySet();

    @Override // it.unimi.dsi.fastutil.objects.Object2BooleanMap
    ObjectSortedSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet();

    @Override // java.util.SortedMap
    Object2BooleanSortedMap<K> subMap(K k, K k2);

    @Override // java.util.SortedMap
    Object2BooleanSortedMap<K> tailMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2BooleanMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Boolean> values2();

    @Override // it.unimi.dsi.fastutil.objects.Object2BooleanMap, java.util.Map
    @Deprecated
    default ObjectSortedSet<Map.Entry<K, Boolean>> entrySet() {
        return object2BooleanEntrySet();
    }
}
