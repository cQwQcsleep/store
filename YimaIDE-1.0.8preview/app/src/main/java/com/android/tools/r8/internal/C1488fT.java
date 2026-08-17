package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1488fT extends AbstractC1829jT implements HU {
    public final /* synthetic */ C2000lT g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1488fT(C2000lT c2000lT) {
        super(c2000lT);
        this.g = c2000lT;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return this.g.c[b()];
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return this.g.c[d()];
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
