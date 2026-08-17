package com.android.tools.r8.internal;

import java.util.ListIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Dk0 implements ListIterator {
    public final ListIterator b;

    public Dk0(Hk0 hk0, int i) {
        this.b = hk0.b.listIterator(i);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.b.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.b.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return (String) this.b.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.b.nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return (String) this.b.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.b.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
