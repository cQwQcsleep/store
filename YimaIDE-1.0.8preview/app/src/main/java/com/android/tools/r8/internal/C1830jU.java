package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jU, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1830jU extends AbstractC2172nU implements HU {
    public final /* synthetic */ C2344pU g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1830jU(C2344pU c2344pU) {
        super(c2344pU);
        this.g = c2344pU;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return this.g.b[b()];
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return this.g.b[d()];
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
