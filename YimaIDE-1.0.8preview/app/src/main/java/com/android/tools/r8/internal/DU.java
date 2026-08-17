package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class DU extends AbstractC1198c1 {
    public final Object b;
    public int c;

    public DU(Object obj) {
        this.b = obj;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.c == 0;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.c == 1;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext()) {
            this.c = 1;
            return this.b;
        }
        z0e.a();
        return null;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.c;
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        if (hasPrevious()) {
            this.c = 0;
            return this.b;
        }
        z0e.a();
        return null;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.c - 1;
    }
}
