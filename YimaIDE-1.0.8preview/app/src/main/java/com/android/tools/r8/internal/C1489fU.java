package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1489fU extends AbstractC1403eU implements HU {
    public C1489fU(C1574gU c1574gU) {
        super(c1574gU);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AbstractC1403eU, java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return a().c;
    }

    @Override // com.android.tools.r8.internal.AbstractC1403eU, java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return b().c;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
