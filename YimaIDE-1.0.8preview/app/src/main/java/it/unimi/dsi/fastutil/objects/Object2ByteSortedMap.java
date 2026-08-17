package it.unimi.dsi.fastutil.objects;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Object2ByteSortedMap<K> extends Object2ByteMap<K>, SortedMap<K, Byte> {
    @Override // java.util.SortedMap
    Object2ByteSortedMap<K> headMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2ByteMap, java.util.Map
    ObjectSortedSet<K> keySet();

    @Override // it.unimi.dsi.fastutil.objects.Object2ByteMap
    ObjectSortedSet<Object2ByteMap.Entry<K>> object2ByteEntrySet();

    @Override // java.util.SortedMap
    Object2ByteSortedMap<K> subMap(K k, K k2);

    @Override // java.util.SortedMap
    Object2ByteSortedMap<K> tailMap(K k);

    @Override // it.unimi.dsi.fastutil.objects.Object2ByteMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Byte> values2();

    @Override // it.unimi.dsi.fastutil.objects.Object2ByteMap, java.util.Map
    @Deprecated
    default ObjectSortedSet<Map.Entry<K, Byte>> entrySet() {
        return object2ByteEntrySet();
    }
}
