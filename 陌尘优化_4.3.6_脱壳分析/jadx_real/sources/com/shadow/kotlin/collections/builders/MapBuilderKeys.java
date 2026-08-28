package com.shadow.kotlin.collections.builders;

import com.shadow.kotlin.io.CloseableKt;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.AbstractMutableSet;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class MapBuilderKeys<E> extends AbstractMutableSet<E> {
    private final kotlin.collections.builders.MapBuilder<E, ?> backing;

    public MapBuilderKeys(kotlin.collections.builders.MapBuilder<E, ?> mapBuilder) {
        CloseableKt.checkNotNullParameter(mapBuilder, "backing");
        this.backing = mapBuilder;
    }

    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection<? extends E> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        this.backing.clear();
    }

    public final boolean contains(Object obj) {
        return this.backing.containsKey(obj);
    }

    public final int getSize() {
        return this.backing.size();
    }

    public final boolean isEmpty() {
        return this.backing.isEmpty();
    }

    public final Iterator<E> iterator() {
        return this.backing.keysIterator$kotlin_stdlib();
    }

    public final boolean remove(Object obj) {
        return this.backing.removeKey$kotlin_stdlib(obj) >= 0;
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
