package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Mx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0683Mx extends C0657Lx implements InterfaceC2067mA {
    public C0683Mx(C0735Ox c0735Ox) {
        super(c0735Ox);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1045aA
    public final int g() {
        return b().b;
    }

    @Override // com.android.tools.r8.internal.AbstractC0761Px, java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return Integer.valueOf(a().b);
    }

    @Override // com.android.tools.r8.internal.AbstractC0761Px, java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
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
