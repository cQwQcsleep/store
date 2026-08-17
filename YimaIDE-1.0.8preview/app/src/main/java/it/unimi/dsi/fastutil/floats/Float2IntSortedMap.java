package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Float2IntSortedMap extends Float2IntMap, SortedMap<Float, Integer> {
    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: comparator, reason: merged with bridge method [inline-methods] */
    Comparator<? super Float> comparator2();

    float firstFloatKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Float firstKey() {
        return Float.valueOf(firstFloatKey());
    }

    @Override // it.unimi.dsi.fastutil.floats.Float2IntMap
    ObjectSortedSet<Float2IntMap.Entry> float2IntEntrySet();

    Float2IntSortedMap headMap(float f);

    @Override // java.util.SortedMap
    @Deprecated
    default Float2IntSortedMap headMap(Float f) {
        return headMap(f.floatValue());
    }

    @Override // it.unimi.dsi.fastutil.floats.Float2IntMap, java.util.Map
    Set<Float> keySet();

    float lastFloatKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Float lastKey() {
        return Float.valueOf(lastFloatKey());
    }

    Float2IntSortedMap subMap(float f, float f2);

    @Override // java.util.SortedMap
    @Deprecated
    default Float2IntSortedMap subMap(Float f, Float f2) {
        return subMap(f.floatValue(), f2.floatValue());
    }

    Float2IntSortedMap tailMap(float f);

    @Override // java.util.SortedMap
    @Deprecated
    default Float2IntSortedMap tailMap(Float f) {
        return tailMap(f.floatValue());
    }

    @Override // it.unimi.dsi.fastutil.floats.Float2IntMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Integer> values2();

    @Override // it.unimi.dsi.fastutil.floats.Float2IntMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Float, Integer>> entrySet() {
        return float2IntEntrySet();
    }
}
