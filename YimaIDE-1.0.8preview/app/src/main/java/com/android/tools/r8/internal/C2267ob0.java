package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ob0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2267ob0 extends AbstractC1279d0 {
    public int d = -1;
    public final /* synthetic */ C2353pb0 e;

    public C2267ob0(C2353pb0 c2353pb0) {
        this.e = c2353pb0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1279d0
    public final Object a() {
        int iNextSetBit = this.e.b.nextSetBit(this.d + 1);
        this.d = iNextSetBit;
        if (iNextSetBit != -1) {
            return this.e.c.e.c.keySet().a().get(this.d);
        }
        b();
        return null;
    }
}
