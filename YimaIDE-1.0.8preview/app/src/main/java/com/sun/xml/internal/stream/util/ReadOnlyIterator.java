package com.sun.xml.internal.stream.util;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ReadOnlyIterator<T> implements Iterator<T> {
    Iterator<T> iterator;

    public ReadOnlyIterator() {
        this.iterator = null;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        Iterator<T> it = this.iterator;
        if (it != null) {
            return it.hasNext();
        }
        return false;
    }

    @Override // java.util.Iterator
    public T next() {
        Iterator<T> it = this.iterator;
        if (it != null) {
            return it.next();
        }
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported");
    }

    public ReadOnlyIterator(Iterator<T> it) {
        this.iterator = it;
    }
}
