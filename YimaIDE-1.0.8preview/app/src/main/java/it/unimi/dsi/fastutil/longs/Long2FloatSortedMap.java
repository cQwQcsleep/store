package it.unimi.dsi.fastutil.longs;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Long2FloatSortedMap extends Long2FloatMap, SortedMap<Long, Float> {
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

    Long2FloatSortedMap headMap(long j);

    @Override // java.util.SortedMap
    @Deprecated
    default Long2FloatSortedMap headMap(Long l) {
        return headMap(l.longValue());
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2FloatMap, java.util.Map
    Set<Long> keySet();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Long lastKey() {
        return Long.valueOf(lastLongKey());
    }

    long lastLongKey();

    @Override // it.unimi.dsi.fastutil.longs.Long2FloatMap
    ObjectSortedSet<Long2FloatMap.Entry> long2FloatEntrySet();

    Long2FloatSortedMap subMap(long j, long j2);

    @Override // java.util.SortedMap
    @Deprecated
    default Long2FloatSortedMap subMap(Long l, Long l2) {
        return subMap(l.longValue(), l2.longValue());
    }

    Long2FloatSortedMap tailMap(long j);

    @Override // java.util.SortedMap
    @Deprecated
    default Long2FloatSortedMap tailMap(Long l) {
        return tailMap(l.longValue());
    }

    @Override // it.unimi.dsi.fastutil.longs.Long2FloatMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Float> values2();

    @Override // it.unimi.dsi.fastutil.longs.Long2FloatMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Long, Float>> entrySet() {
        return long2FloatEntrySet();
    }
}
