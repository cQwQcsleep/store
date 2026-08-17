package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Short2IntSortedMap extends Short2IntMap, SortedMap<Short, Integer> {
    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: comparator, reason: merged with bridge method [inline-methods] */
    Comparator<? super Short> comparator2();

    @Override // java.util.SortedMap
    @Deprecated
    default Short firstKey() {
        return Short.valueOf(firstShortKey());
    }

    short firstShortKey();

    @Override // java.util.SortedMap
    @Deprecated
    default Short2IntSortedMap headMap(Short sh) {
        return headMap(sh.shortValue());
    }

    Short2IntSortedMap headMap(short s);

    @Override // it.unimi.dsi.fastutil.shorts.Short2IntMap, java.util.Map
    Set<Short> keySet();

    @Override // java.util.SortedMap
    @Deprecated
    default Short lastKey() {
        return Short.valueOf(lastShortKey());
    }

    short lastShortKey();

    @Override // it.unimi.dsi.fastutil.shorts.Short2IntMap
    ObjectSortedSet<Short2IntMap.Entry> short2IntEntrySet();

    @Override // java.util.SortedMap
    @Deprecated
    default Short2IntSortedMap subMap(Short sh, Short sh2) {
        return subMap(sh.shortValue(), sh2.shortValue());
    }

    Short2IntSortedMap subMap(short s, short s2);

    @Override // java.util.SortedMap
    @Deprecated
    default Short2IntSortedMap tailMap(Short sh) {
        return tailMap(sh.shortValue());
    }

    Short2IntSortedMap tailMap(short s);

    @Override // it.unimi.dsi.fastutil.shorts.Short2IntMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Integer> values2();

    @Override // it.unimi.dsi.fastutil.shorts.Short2IntMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Short, Integer>> entrySet() {
        return short2IntEntrySet();
    }
}
