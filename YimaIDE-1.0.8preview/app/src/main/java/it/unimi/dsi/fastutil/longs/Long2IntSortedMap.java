package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2IntSortedMap extends Long2IntMap, SortedMap<Long, Integer> {
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

    Long2IntSortedMap headMap(long j);

    @Override // java.util.SortedMap
    @Deprecated
    default Long2IntSortedMap headMap(Long l) {
        return headMap(l.longValue());
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2IntMap, java.util.Map
    Set<Long> keySet();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Long lastKey() {
        return Long.valueOf(lastLongKey());
    }

    long lastLongKey();

    @Override // it.unimi.dsi.fastutil.longs.Long2IntMap
    ObjectSortedSet<Long2IntMap.Entry> long2IntEntrySet();

    Long2IntSortedMap subMap(long j, long j2);

    @Override // java.util.SortedMap
    @Deprecated
    default Long2IntSortedMap subMap(Long l, Long l2) {
        return subMap(l.longValue(), l2.longValue());
    }

    Long2IntSortedMap tailMap(long j);

    @Override // java.util.SortedMap
    @Deprecated
    default Long2IntSortedMap tailMap(Long l) {
        return tailMap(l.longValue());
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2IntMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Integer> values2();

    @Override // it.unimi.dsi.fastutil.longs.Long2IntMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Long, Integer>> entrySet() {
        return long2IntEntrySet();
    }
}
