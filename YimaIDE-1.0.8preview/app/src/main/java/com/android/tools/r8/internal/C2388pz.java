package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2388pz extends AbstractC2814uz implements BU {
    public C2643sz h;
    public final /* synthetic */ C2986wz i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2388pz(C2986wz c2986wz) {
        super(c2986wz);
        this.i = c2986wz;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C2643sz c2643sz = new C2643sz(this.i, a());
        this.h = c2643sz;
        return c2643sz;
    }

    @Override // com.android.tools.r8.internal.AbstractC2814uz, java.util.Iterator
    public final void remove() {
        super.remove();
        this.h.b = -1;
    }
}
