package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ky, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0633Ky extends AbstractC0892Uy implements InterfaceC2067mA {
    public C0633Ky(C0944Wy c0944Wy) {
        super(c0944Wy);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1045aA
    public final int g() {
        return b().b;
    }

    @Override // com.android.tools.r8.internal.AbstractC0892Uy, java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return Integer.valueOf(a().b);
    }

    @Override // com.android.tools.r8.internal.AbstractC0892Uy, java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return Integer.valueOf(b().b);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1640hA
    public final int q() {
        return a().b;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
