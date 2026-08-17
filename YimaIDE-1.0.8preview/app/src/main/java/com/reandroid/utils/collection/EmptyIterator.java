package com.reandroid.utils.collection;

import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EmptyIterator<T> implements ListIterator<T>, EmptyItem {
    private static final EmptyIterator<?> INS = new EmptyIterator<>();

    public static <T1> EmptyIterator<T1> of() {
        return (EmptyIterator<T1>) INS;
    }

    @Override // java.util.ListIterator
    public void add(T t) {
        throw new IllegalArgumentException("Empty iterator");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return false;
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        return false;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public T next() {
        throw new IllegalArgumentException("Empty iterator");
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return -1;
    }

    @Override // java.util.ListIterator
    public T previous() {
        throw new IllegalArgumentException("Empty iterator");
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return -1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new IllegalArgumentException("Empty iterator");
    }

    @Override // java.util.ListIterator
    public void set(T t) {
        throw new IllegalArgumentException("Empty iterator");
    }
}
