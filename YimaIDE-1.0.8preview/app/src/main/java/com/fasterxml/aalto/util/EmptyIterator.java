package com.fasterxml.aalto.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EmptyIterator<T> implements Iterator<T> {
    static final EmptyIterator<Object> sInstance = new EmptyIterator<>();

    private EmptyIterator() {
    }

    public static <T> EmptyIterator<T> getInstance() {
        return (EmptyIterator<T>) sInstance;
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
