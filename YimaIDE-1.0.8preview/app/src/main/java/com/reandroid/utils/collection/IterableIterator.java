package com.reandroid.utils.collection;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class IterableIterator<E, T> implements Iterator<T> {
    private final Iterator<? extends E> iterator;
    private int mCount;
    private Iterator<T> mCurrent;
    private boolean mStop;

    public IterableIterator(Iterator<? extends E> it) {
        this.iterator = it;
    }

    private Iterator<T> getCurrent() {
        Iterator<T> it = this.mCurrent;
        if (it == null || !it.hasNext()) {
            this.mCurrent = null;
            while (this.iterator.hasNext()) {
                Iterator<T> it2 = iterator(this.iterator.next());
                if (it2 != null && it2.hasNext()) {
                    this.mCurrent = it2;
                    break;
                }
            }
        }
        return this.mCurrent;
    }

    public int getCountValue() {
        return this.mCount;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        Iterator<T> current = getCurrent();
        return (current == null || this.mStop || !current.hasNext()) ? false : true;
    }

    public abstract Iterator<T> iterator(E e);

    @Override // java.util.Iterator
    public T next() {
        Iterator<T> current = getCurrent();
        if (current == null) {
            z0e.a();
            return null;
        }
        T next = current.next();
        this.mCount++;
        return next;
    }

    public void stop() {
        this.mStop = true;
    }
}
