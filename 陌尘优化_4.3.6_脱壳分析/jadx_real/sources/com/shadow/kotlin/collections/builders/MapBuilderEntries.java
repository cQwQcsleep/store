package com.shadow.kotlin.collections.builders;

import com.shadow.kotlin.io.CloseableKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.collections.AbstractMutableSet;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class MapBuilderEntries<K, V> extends AbstractMutableSet<Map.Entry<Object, Object>> {
    private final kotlin.collections.builders.MapBuilder<K, V> backing;

    public MapBuilderEntries(kotlin.collections.builders.MapBuilder<K, V> mapBuilder) {
        CloseableKt.checkNotNullParameter(mapBuilder, "backing");
        this.backing = mapBuilder;
    }

    public final boolean add(Object obj) {
        CloseableKt.checkNotNullParameter((Map.Entry) obj, "element");
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        this.backing.clear();
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry<? extends K, ? extends V> entry = (Map.Entry) obj;
        CloseableKt.checkNotNullParameter(entry, "element");
        return this.backing.containsEntry$kotlin_stdlib(entry);
    }

    public final boolean containsAll(Collection<? extends Object> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        return this.backing.containsAllEntries$kotlin_stdlib(collection);
    }

    public final int getSize() {
        return this.backing.size();
    }

    public final boolean isEmpty() {
        return this.backing.isEmpty();
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return this.backing.entriesIterator$kotlin_stdlib();
    }

    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry<? extends K, ? extends V> entry = (Map.Entry) obj;
        CloseableKt.checkNotNullParameter(entry, "element");
        return this.backing.removeEntry$kotlin_stdlib(entry);
    }

    public final boolean removeAll(Collection<? extends Object> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.removeAll(collection);
    }

    public final boolean retainAll(Collection<? extends Object> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.retainAll(collection);
    }
}
