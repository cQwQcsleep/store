package com.intellij.util;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class UnmodifiableIterator<T> implements Iterator<T> {
    private final Iterator<? extends T> myOriginalIterator;

    public UnmodifiableIterator(Iterator<? extends T> it) {
        this.myOriginalIterator = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.myOriginalIterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        return this.myOriginalIterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
