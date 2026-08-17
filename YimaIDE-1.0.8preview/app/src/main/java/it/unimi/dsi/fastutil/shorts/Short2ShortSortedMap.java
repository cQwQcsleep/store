package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Short2ShortSortedMap extends Short2ShortMap, SortedMap<Short, Short> {
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
    default Short2ShortSortedMap headMap(Short sh) {
        return headMap(sh.shortValue());
    }

    Short2ShortSortedMap headMap(short s);

    @Override // it.unimi.dsi.fastutil.shorts.Short2ShortMap, java.util.Map
    Set<Short> keySet();

    @Override // java.util.SortedMap
    @Deprecated
    default Short lastKey() {
        return Short.valueOf(lastShortKey());
    }

    short lastShortKey();

    @Override // it.unimi.dsi.fastutil.shorts.Short2ShortMap
    ObjectSortedSet<Short2ShortMap.Entry> short2ShortEntrySet();

    @Override // java.util.SortedMap
    @Deprecated
    default Short2ShortSortedMap subMap(Short sh, Short sh2) {
        return subMap(sh.shortValue(), sh2.shortValue());
    }

    Short2ShortSortedMap subMap(short s, short s2);

    @Override // java.util.SortedMap
    @Deprecated
    default Short2ShortSortedMap tailMap(Short sh) {
        return tailMap(sh.shortValue());
    }

    Short2ShortSortedMap tailMap(short s);

    @Override // it.unimi.dsi.fastutil.shorts.Short2ShortMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Short> values2();

    @Override // it.unimi.dsi.fastutil.shorts.Short2ShortMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Short, Short>> entrySet() {
        return short2ShortEntrySet();
    }
}
