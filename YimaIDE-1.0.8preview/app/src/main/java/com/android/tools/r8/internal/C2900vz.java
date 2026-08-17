package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2900vz extends AbstractC2814uz implements BU {
    public final /* synthetic */ C2986wz h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2900vz(C2986wz c2986wz) {
        super(c2986wz);
        this.h = c2986wz;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.h.d[a()];
    }
}
