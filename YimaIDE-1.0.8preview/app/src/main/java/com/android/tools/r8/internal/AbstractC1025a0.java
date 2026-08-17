package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1025a0 extends W implements InterfaceC2067mA, InterfaceC1045aA {
    public void a(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        a(((Integer) obj).intValue());
    }

    public void b(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return Integer.valueOf(g());
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        b(((Integer) obj).intValue());
    }
}
