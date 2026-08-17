package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0502Fx extends AbstractC0761Px implements InterfaceC2067mA {
    public C0502Fx(C0813Rx c0813Rx) {
        super(c0813Rx);
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
