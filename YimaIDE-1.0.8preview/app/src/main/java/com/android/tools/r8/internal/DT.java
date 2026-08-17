package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class DT extends CT implements InterfaceC1481fM {
    public final /* synthetic */ ET h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DT(ET et) {
        super(et);
        this.h = et;
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
