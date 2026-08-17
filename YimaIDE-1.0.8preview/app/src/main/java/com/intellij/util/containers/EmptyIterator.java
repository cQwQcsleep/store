package com.intellij.util.containers;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Deprecated
class EmptyIterator<T> implements Iterator<T> {
    private static final EmptyIterator INSTANCE = new EmptyIterator();

    public static <T> EmptyIterator<T> getInstance() {
        return INSTANCE;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return false;
    }

    @Override // java.util.Iterator
    public T next() {
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new IllegalStateException();
    }
}
