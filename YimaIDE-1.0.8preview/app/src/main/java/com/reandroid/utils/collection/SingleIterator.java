package com.reandroid.utils.collection;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SingleIterator<T> implements Iterator<T> {
    private T mItem;

    public SingleIterator(T t) {
        this.mItem = t;
    }

    public static <T1> Iterator<T1> of(T1 t1) {
        return t1 == null ? EmptyIterator.of() : new SingleIterator(t1);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.mItem != null;
    }

    @Override // java.util.Iterator
    public T next() {
        T t = this.mItem;
        this.mItem = null;
        return t;
    }
}
