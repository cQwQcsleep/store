package com.shadow.kotlin.collections.builders;

import com.shadow.kotlin.io.CloseableKt;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.AbstractMutableSet;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class SetBuilder<E> extends AbstractMutableSet<E> implements Set<E>, Serializable {
    private static final SetBuilder Empty;
    private final kotlin.collections.builders.MapBuilder<E, ?> backing;

    static {
        MapBuilder.Companion.getClass();
        Empty = new SetBuilder(MapBuilder.Empty);
    }

    public SetBuilder(kotlin.collections.builders.MapBuilder<E, ?> mapBuilder) {
        CloseableKt.checkNotNullParameter(mapBuilder, "backing");
        this.backing = mapBuilder;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.backing.isReadOnly$kotlin_stdlib()) {
            return new SerializedCollection(this, 1);
        }
        throw new NotSerializableException("The set cannot be serialized while it is being built.");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(E e) {
        return this.backing.addKey$kotlin_stdlib(e) >= 0;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.addAll(collection);
    }

    public final Set<E> build() {
        this.backing.build();
        return size() > 0 ? this : Empty;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.backing.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        return this.backing.containsKey(obj);
    }

    public int getSize() {
        return this.backing.size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.backing.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.backing.keysIterator$kotlin_stdlib();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        return this.backing.removeKey$kotlin_stdlib(obj) >= 0;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.removeAll(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        CloseableKt.checkNotNullParameter(collection, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.retainAll(collection);
    }

    public SetBuilder() {
        this(new MapBuilder());
    }

    public SetBuilder(int i) {
        this(new MapBuilder(i));
    }
}
