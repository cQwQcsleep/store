package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Int2BooleanSortedMap extends Int2BooleanMap, SortedMap<Integer, Boolean> {
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

    Int2BooleanSortedMap headMap(int i);

    @Override // java.util.SortedMap
    @Deprecated
    default Int2BooleanSortedMap headMap(Integer num) {
        return headMap(num.intValue());
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2BooleanMap
    ObjectSortedSet<Int2BooleanMap.Entry> int2BooleanEntrySet();

    @Override // it.unimi.dsi.fastutil.ints.Int2BooleanMap, java.util.Map
    Set<Integer> keySet();

    int lastIntKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Integer lastKey() {
        return Integer.valueOf(lastIntKey());
    }

    Int2BooleanSortedMap subMap(int i, int i2);

    @Override // java.util.SortedMap
    @Deprecated
    default Int2BooleanSortedMap subMap(Integer num, Integer num2) {
        return subMap(num.intValue(), num2.intValue());
    }

    Int2BooleanSortedMap tailMap(int i);

    @Override // java.util.SortedMap
    @Deprecated
    default Int2BooleanSortedMap tailMap(Integer num) {
        return tailMap(num.intValue());
    }

    @Override // it.unimi.dsi.fastutil.ints.Int2BooleanMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Boolean> values2();

    @Override // it.unimi.dsi.fastutil.ints.Int2BooleanMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Integer, Boolean>> entrySet() {
        return int2BooleanEntrySet();
    }
}
