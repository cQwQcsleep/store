package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UT extends AbstractC1403eU implements HU {
    public UT(C1574gU c1574gU) {
        super(c1574gU);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AbstractC1403eU, java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return a().b;
    }

    @Override // com.android.tools.r8.internal.AbstractC1403eU, java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return b().b;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
