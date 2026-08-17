package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1151bU extends C1065aU implements HU {
    public C1151bU(C1318dU c1318dU) {
        super(c1318dU);
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
