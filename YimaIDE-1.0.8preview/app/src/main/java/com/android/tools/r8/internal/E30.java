package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class E30 extends I30 implements BU {
    public final /* synthetic */ K30 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E30(K30 k30) {
        super(k30);
        this.h = k30;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.h.b[a()];
    }
}
