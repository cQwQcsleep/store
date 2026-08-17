package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Double2BooleanSortedMap extends Double2BooleanMap, SortedMap<Double, Boolean> {
    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: comparator, reason: merged with bridge method [inline-methods] */
    Comparator<? super Double> comparator2();

    @Override // it.unimi.dsi.fastutil.doubles.Double2BooleanMap
    ObjectSortedSet<Double2BooleanMap.Entry> double2BooleanEntrySet();

    double firstDoubleKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Double firstKey() {
        return Double.valueOf(firstDoubleKey());
    }

    Double2BooleanSortedMap headMap(double d);

    @Override // java.util.SortedMap
    @Deprecated
    default Double2BooleanSortedMap headMap(Double d) {
        return headMap(d.doubleValue());
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2BooleanMap, java.util.Map
    Set<Double> keySet();

    double lastDoubleKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Double lastKey() {
        return Double.valueOf(lastDoubleKey());
    }

    Double2BooleanSortedMap subMap(double d, double d2);

    @Override // java.util.SortedMap
    @Deprecated
    default Double2BooleanSortedMap subMap(Double d, Double d2) {
        return subMap(d.doubleValue(), d2.doubleValue());
    }

    Double2BooleanSortedMap tailMap(double d);

    @Override // java.util.SortedMap
    @Deprecated
    default Double2BooleanSortedMap tailMap(Double d) {
        return tailMap(d.doubleValue());
    }

    @Override // it.unimi.dsi.fastutil.doubles.Double2BooleanMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Boolean> values2();

    @Override // it.unimi.dsi.fastutil.doubles.Double2BooleanMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Double, Boolean>> entrySet() {
        return double2BooleanEntrySet();
    }
}
