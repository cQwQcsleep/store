package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2343pT extends AbstractC2684tT implements BU {
    public final /* synthetic */ C2855vT h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2343pT(C2855vT c2855vT) {
        super(c2855vT);
        this.h = c2855vT;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.h.c[a()];
    }
}
