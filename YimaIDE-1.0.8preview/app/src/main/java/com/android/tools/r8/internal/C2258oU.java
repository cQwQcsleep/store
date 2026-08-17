package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2258oU extends AbstractC2172nU implements HU {
    public final /* synthetic */ C2344pU g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2258oU(C2344pU c2344pU) {
        super(c2344pU);
        this.g = c2344pU;
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
