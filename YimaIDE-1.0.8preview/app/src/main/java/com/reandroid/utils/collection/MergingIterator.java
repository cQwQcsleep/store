package com.reandroid.utils.collection;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MergingIterator<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> iteratorIterator;
    private Iterator<T> mCurrent;

    public MergingIterator(Iterator<Iterator<T>> it) {
        this.iteratorIterator = it;
    }

    private Iterator<T> getCurrent() {
        Iterator<T> next = this.mCurrent;
        while (true) {
            if (next != null && next.hasNext()) {
                this.mCurrent = next;
                return next;
            }
            if (!this.iteratorIterator.hasNext()) {
                return null;
            }
            next = this.iteratorIterator.next();
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        Iterator<T> current = getCurrent();
        return current != null && current.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        Iterator<T> current = getCurrent();
        if (current != null) {
            return current.next();
        }
        z0e.a();
        return null;
    }
}
