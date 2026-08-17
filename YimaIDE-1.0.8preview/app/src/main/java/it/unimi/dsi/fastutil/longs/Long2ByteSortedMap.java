package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2ByteSortedMap extends Long2ByteMap, SortedMap<Long, Byte> {
    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: comparator, reason: merged with bridge method [inline-methods] */
    Comparator<? super Long> comparator2();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Long firstKey() {
        return Long.valueOf(firstLongKey());
    }

    long firstLongKey();

    Long2ByteSortedMap headMap(long j);

    @Override // java.util.SortedMap
    @Deprecated
    default Long2ByteSortedMap headMap(Long l) {
        return headMap(l.longValue());
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2ByteMap, java.util.Map
    Set<Long> keySet();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Long lastKey() {
        return Long.valueOf(lastLongKey());
    }

    long lastLongKey();

    @Override // it.unimi.dsi.fastutil.longs.Long2ByteMap
    ObjectSortedSet<Long2ByteMap.Entry> long2ByteEntrySet();

    Long2ByteSortedMap subMap(long j, long j2);

    @Override // java.util.SortedMap
    @Deprecated
    default Long2ByteSortedMap subMap(Long l, Long l2) {
        return subMap(l.longValue(), l2.longValue());
    }

    Long2ByteSortedMap tailMap(long j);

    @Override // java.util.SortedMap
    @Deprecated
    default Long2ByteSortedMap tailMap(Long l) {
        return tailMap(l.longValue());
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2ByteMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Byte> values2();

    @Override // it.unimi.dsi.fastutil.longs.Long2ByteMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Long, Byte>> entrySet() {
        return long2ByteEntrySet();
    }
}
