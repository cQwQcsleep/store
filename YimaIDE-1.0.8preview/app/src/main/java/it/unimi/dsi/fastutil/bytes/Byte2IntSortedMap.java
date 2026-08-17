package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2IntSortedMap extends Byte2IntMap, SortedMap<Byte, Integer> {
    @Override // it.unimi.dsi.fastutil.bytes.Byte2IntMap
    ObjectSortedSet<Byte2IntMap.Entry> byte2IntEntrySet();

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

    Byte2IntSortedMap headMap(byte b);

    @Override // java.util.SortedMap
    @Deprecated
    default Byte2IntSortedMap headMap(Byte b) {
        return headMap(b.byteValue());
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2IntMap, java.util.Map
    Set<Byte> keySet();

    byte lastByteKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Byte lastKey() {
        return Byte.valueOf(lastByteKey());
    }

    Byte2IntSortedMap subMap(byte b, byte b2);

    @Override // java.util.SortedMap
    @Deprecated
    default Byte2IntSortedMap subMap(Byte b, Byte b2) {
        return subMap(b.byteValue(), b2.byteValue());
    }

    Byte2IntSortedMap tailMap(byte b);

    @Override // java.util.SortedMap
    @Deprecated
    default Byte2IntSortedMap tailMap(Byte b) {
        return tailMap(b.byteValue());
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2IntMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Integer> values2();

    @Override // it.unimi.dsi.fastutil.bytes.Byte2IntMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Byte, Integer>> entrySet() {
        return byte2IntEntrySet();
    }
}
