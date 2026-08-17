package com.android.tools.r8.internal;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Zk0 extends AbstractC1180bl0 {
    public Zk0(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.android.tools.r8.internal.AbstractC1180bl0
    public final void a(Object obj, long j, byte b) {
        if (AbstractC1263cl0.f) {
            long j2 = (-4) & j;
            AbstractC1180bl0 abstractC1180bl0 = AbstractC1263cl0.b;
            int iB = abstractC1180bl0.b(obj, j2);
            int i = ((~((int) j)) & 3) << 3;
            abstractC1180bl0.a(obj, j2, ((b & 255) << i) | ((~(255 << i)) & iB));
            return;
        }
        long j3 = (-4) & j;
        AbstractC1180bl0 abstractC1180bl1 = AbstractC1263cl0.b;
        int i2 = (((int) j) & 3) << 3;
        abstractC1180bl1.a(obj, j3, ((b & 255) << i2) | ((~(255 << i2)) & abstractC1180bl1.b(obj, j3)));
    }

    @Override // com.android.tools.r8.internal.AbstractC1180bl0
    public final boolean b() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1180bl0
    public final byte a(Object obj, long j) {
        int iB;
        long j2;
        if (AbstractC1263cl0.f) {
            iB = AbstractC1263cl0.b.b(obj, (-4) & j);
            j2 = (~j) & 3;
        } else {
            iB = AbstractC1263cl0.b.b(obj, (-4) & j);
            j2 = j & 3;
        }
        return (byte) ((iB >>> ((int) (j2 << 3))) & 255);
    }
}
