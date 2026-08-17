package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2DoubleSortedMap extends Byte2DoubleMap, SortedMap<Byte, Double> {
    @Override // it.unimi.dsi.fastutil.bytes.Byte2DoubleMap
    ObjectSortedSet<Byte2DoubleMap.Entry> byte2DoubleEntrySet();

    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: comparator, reason: merged with bridge method [inline-methods] */
    Comparator<? super Byte> comparator2();

    byte firstByteKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Byte firstKey() {
        return Byte.valueOf(firstByteKey());
    }

    Byte2DoubleSortedMap headMap(byte b);

    @Override // java.util.SortedMap
    @Deprecated
    default Byte2DoubleSortedMap headMap(Byte b) {
        return headMap(b.byteValue());
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2DoubleMap, java.util.Map
    Set<Byte> keySet();

    byte lastByteKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Byte lastKey() {
        return Byte.valueOf(lastByteKey());
    }

    Byte2DoubleSortedMap subMap(byte b, byte b2);

    @Override // java.util.SortedMap
    @Deprecated
    default Byte2DoubleSortedMap subMap(Byte b, Byte b2) {
        return subMap(b.byteValue(), b2.byteValue());
    }

    Byte2DoubleSortedMap tailMap(byte b);

    @Override // java.util.SortedMap
    @Deprecated
    default Byte2DoubleSortedMap tailMap(Byte b) {
        return tailMap(b.byteValue());
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2DoubleMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Double> values2();

    @Override // it.unimi.dsi.fastutil.bytes.Byte2DoubleMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Byte, Double>> entrySet() {
        return byte2DoubleEntrySet();
    }
}
