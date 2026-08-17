package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1402eT extends AbstractC1829jT implements HU {
    public C1659hT g;
    public final /* synthetic */ C2000lT h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1402eT(C2000lT c2000lT) {
        super(c2000lT);
        this.h = c2000lT;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        C1659hT c1659hT = new C1659hT(this.h, b());
        this.g = c1659hT;
        return c1659hT;
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        C1659hT c1659hT = new C1659hT(this.h, d());
        this.g = c1659hT;
        return c1659hT;
    }

    @Override // com.android.tools.r8.internal.AbstractC1829jT, java.util.ListIterator, java.util.Iterator
    public final void remove() {
        super.remove();
        this.g.b = -1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
