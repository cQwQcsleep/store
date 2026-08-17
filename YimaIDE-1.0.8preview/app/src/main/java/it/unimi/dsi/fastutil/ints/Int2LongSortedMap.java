package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Int2LongSortedMap extends Int2LongMap, SortedMap<Integer, Long> {
    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: comparator, reason: merged with bridge method [inline-methods] */
    Comparator<? super Integer> comparator2();

    int firstIntKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Integer firstKey() {
        return Integer.valueOf(firstIntKey());
    }

    Int2LongSortedMap headMap(int i);

    @Override // java.util.SortedMap
    @Deprecated
    default Int2LongSortedMap headMap(Integer num) {
        return headMap(num.intValue());
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2LongMap
    ObjectSortedSet<Int2LongMap.Entry> int2LongEntrySet();

    @Override // it.unimi.dsi.fastutil.ints.Int2LongMap, java.util.Map
    Set<Integer> keySet();

    int lastIntKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Integer lastKey() {
        return Integer.valueOf(lastIntKey());
    }

    Int2LongSortedMap subMap(int i, int i2);

    @Override // java.util.SortedMap
    @Deprecated
    default Int2LongSortedMap subMap(Integer num, Integer num2) {
        return subMap(num.intValue(), num2.intValue());
    }

    Int2LongSortedMap tailMap(int i);

    @Override // java.util.SortedMap
    @Deprecated
    default Int2LongSortedMap tailMap(Integer num) {
        return tailMap(num.intValue());
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2LongMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Long> values2();

    @Override // it.unimi.dsi.fastutil.ints.Int2LongMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Integer, Long>> entrySet() {
        return int2LongEntrySet();
    }
}
