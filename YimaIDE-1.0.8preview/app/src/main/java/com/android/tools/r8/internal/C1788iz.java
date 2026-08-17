package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1788iz extends AbstractC1704hz implements HU {
    public final /* synthetic */ C1874jz g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1788iz(C1874jz c1874jz) {
        super(c1874jz);
        this.g = c1874jz;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return this.g.d[b()];
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return this.g.d[d()];
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
