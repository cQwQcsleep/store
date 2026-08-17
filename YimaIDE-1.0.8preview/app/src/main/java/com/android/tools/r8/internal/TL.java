package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class TL extends XL implements InterfaceC1481fM {
    public final /* synthetic */ ZL h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TL(ZL zl) {
        super(zl);
        this.h = zl;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1481fM
    public final long c() {
        return this.h.b[a()];
    }

    @Override // java.util.Iterator
    public final Object next() {
        return Long.valueOf(this.h.b[a()]);
    }
}
