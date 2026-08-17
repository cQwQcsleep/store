package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Byte2BooleanSortedMap extends Byte2BooleanMap, SortedMap<Byte, Boolean> {
    @Override // it.unimi.dsi.fastutil.bytes.Byte2BooleanMap
    ObjectSortedSet<Byte2BooleanMap.Entry> byte2BooleanEntrySet();

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

    Byte2BooleanSortedMap headMap(byte b);

    @Override // java.util.SortedMap
    @Deprecated
    default Byte2BooleanSortedMap headMap(Byte b) {
        return headMap(b.byteValue());
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2BooleanMap, java.util.Map
    Set<Byte> keySet();

    byte lastByteKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Byte lastKey() {
        return Byte.valueOf(lastByteKey());
    }

    Byte2BooleanSortedMap subMap(byte b, byte b2);

    @Override // java.util.SortedMap
    @Deprecated
    default Byte2BooleanSortedMap subMap(Byte b, Byte b2) {
        return subMap(b.byteValue(), b2.byteValue());
    }

    Byte2BooleanSortedMap tailMap(byte b);

    @Override // java.util.SortedMap
    @Deprecated
    default Byte2BooleanSortedMap tailMap(Byte b) {
        return tailMap(b.byteValue());
    }

    @Override // it.unimi.dsi.fastutil.bytes.Byte2BooleanMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Boolean> values2();

    @Override // it.unimi.dsi.fastutil.bytes.Byte2BooleanMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Byte, Boolean>> entrySet() {
        return byte2BooleanEntrySet();
    }
}
