package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2473qz extends AbstractC2814uz implements InterfaceC1640hA {
    public final /* synthetic */ C2986wz h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2473qz(C2986wz c2986wz) {
        super(c2986wz);
        this.h = c2986wz;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return Integer.valueOf(this.h.c[a()]);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1640hA
    public final int q() {
        return this.h.c[a()];
    }
}
