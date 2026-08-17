package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D30 extends I30 implements BU {
    public G30 h;
    public final /* synthetic */ K30 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D30(K30 k30) {
        super(k30);
        this.i = k30;
    }

    @Override // java.util.Iterator
    public final Object next() {
        G30 g30 = new G30(this.i, a());
        this.h = g30;
        return g30;
    }

    @Override // com.android.tools.r8.internal.I30, java.util.Iterator
    public final void remove() {
        super.remove();
        this.h.b = -1;
    }
}
