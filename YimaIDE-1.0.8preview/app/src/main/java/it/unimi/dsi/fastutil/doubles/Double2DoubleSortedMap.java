package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2DoubleSortedMap extends Double2DoubleMap, SortedMap<Double, Double> {
    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: comparator, reason: merged with bridge method [inline-methods] */
    Comparator<? super Double> comparator2();

    @Override // it.unimi.dsi.fastutil.doubles.Double2DoubleMap
    ObjectSortedSet<Double2DoubleMap.Entry> double2DoubleEntrySet();

    double firstDoubleKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Double firstKey() {
        return Double.valueOf(firstDoubleKey());
    }

    Double2DoubleSortedMap headMap(double d);

    @Override // java.util.SortedMap
    @Deprecated
    default Double2DoubleSortedMap headMap(Double d) {
        return headMap(d.doubleValue());
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2DoubleMap, java.util.Map
    Set<Double> keySet();

    double lastDoubleKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Double lastKey() {
        return Double.valueOf(lastDoubleKey());
    }

    Double2DoubleSortedMap subMap(double d, double d2);

    @Override // java.util.SortedMap
    @Deprecated
    default Double2DoubleSortedMap subMap(Double d, Double d2) {
        return subMap(d.doubleValue(), d2.doubleValue());
    }

    Double2DoubleSortedMap tailMap(double d);

    @Override // java.util.SortedMap
    @Deprecated
    default Double2DoubleSortedMap tailMap(Double d) {
        return tailMap(d.doubleValue());
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2DoubleMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Double> values2();

    @Override // it.unimi.dsi.fastutil.doubles.Double2DoubleMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Double, Double>> entrySet() {
        return double2DoubleEntrySet();
    }
}
