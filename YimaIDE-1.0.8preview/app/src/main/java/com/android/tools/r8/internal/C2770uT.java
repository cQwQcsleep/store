package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2770uT extends AbstractC2684tT implements InterfaceC1640hA {
    public final /* synthetic */ C2855vT h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2770uT(C2855vT c2855vT) {
        super(c2855vT);
        this.h = c2855vT;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return Integer.valueOf(this.h.d[a()]);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1640hA
    public final int q() {
        return this.h.d[a()];
    }
}
