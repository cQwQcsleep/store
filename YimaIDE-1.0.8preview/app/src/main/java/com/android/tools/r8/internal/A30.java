package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A30 extends AbstractC3164z30 implements InterfaceC1481fM {
    public final /* synthetic */ B30 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A30(B30 b30) {
        super(b30);
        this.h = b30;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1481fM
    public final long c() {
        return this.h.c[a()];
    }

    @Override // java.util.Iterator
    public final Object next() {
        return Long.valueOf(this.h.c[a()]);
    }
}
