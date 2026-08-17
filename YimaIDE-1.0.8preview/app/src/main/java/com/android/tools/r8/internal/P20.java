package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P20 extends T20 implements BU {
    public final /* synthetic */ V20 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P20(V20 v20) {
        super(v20);
        this.h = v20;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.h.b[a()];
    }
}
