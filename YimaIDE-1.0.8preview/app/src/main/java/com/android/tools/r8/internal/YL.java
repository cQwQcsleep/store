package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class YL extends XL implements BU {
    public final /* synthetic */ ZL h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public YL(ZL zl) {
        super(zl);
        this.h = zl;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.h.c[a()];
    }
}
