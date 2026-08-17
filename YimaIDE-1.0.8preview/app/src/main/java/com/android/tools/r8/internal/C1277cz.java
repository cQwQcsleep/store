package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1277cz extends AbstractC1704hz implements HU {
    public C1533fz g;
    public final /* synthetic */ C1874jz h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1277cz(C1874jz c1874jz) {
        super(c1874jz);
        this.h = c1874jz;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        C1533fz c1533fz = new C1533fz(this.h, b());
        this.g = c1533fz;
        return c1533fz;
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        C1533fz c1533fz = new C1533fz(this.h, d());
        this.g = c1533fz;
        return c1533fz;
    }

    @Override // com.android.tools.r8.internal.AbstractC1704hz, java.util.ListIterator, java.util.Iterator
    public final void remove() {
        super.remove();
        this.g.b = -1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
