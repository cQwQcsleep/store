package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2IntSortedMap extends Double2IntMap, SortedMap<Double, Integer> {
    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: comparator, reason: merged with bridge method [inline-methods] */
    Comparator<? super Double> comparator2();

    @Override // it.unimi.dsi.fastutil.doubles.Double2IntMap
    ObjectSortedSet<Double2IntMap.Entry> double2IntEntrySet();

    double firstDoubleKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Double firstKey() {
        return Double.valueOf(firstDoubleKey());
    }

    Double2IntSortedMap headMap(double d);

    @Override // java.util.SortedMap
    @Deprecated
    default Double2IntSortedMap headMap(Double d) {
        return headMap(d.doubleValue());
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2IntMap, java.util.Map
    Set<Double> keySet();

    double lastDoubleKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Double lastKey() {
        return Double.valueOf(lastDoubleKey());
    }

    Double2IntSortedMap subMap(double d, double d2);

    @Override // java.util.SortedMap
    @Deprecated
    default Double2IntSortedMap subMap(Double d, Double d2) {
        return subMap(d.doubleValue(), d2.doubleValue());
    }

    Double2IntSortedMap tailMap(double d);

    @Override // java.util.SortedMap
    @Deprecated
    default Double2IntSortedMap tailMap(Double d) {
        return tailMap(d.doubleValue());
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2IntMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Integer> values2();

    @Override // it.unimi.dsi.fastutil.doubles.Double2IntMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Double, Integer>> entrySet() {
        return double2IntEntrySet();
    }
}
