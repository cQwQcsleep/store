package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Vy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0918Vy extends AbstractC0892Uy implements HU {
    public C0918Vy(C0944Wy c0944Wy) {
        super(c0944Wy);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.AbstractC0892Uy, java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return a().c;
    }

    @Override // com.android.tools.r8.internal.AbstractC0892Uy, java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return b().c;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
