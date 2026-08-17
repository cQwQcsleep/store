package com.android.tools.r8.internal;

import java.util.RandomAccess;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1535g0 extends AbstractC1620h0 implements RandomAccess {
    public final AbstractC1620h0 b;
    public final int c;
    public final int d;

    public C1535g0(AbstractC1620h0 abstractC1620h0, int i, int i2) {
        KB.c(abstractC1620h0, "list");
        this.b = abstractC1620h0;
        this.c = i;
        int iA = abstractC1620h0.a();
        if (i < 0 || i2 > iA) {
            en0.a("fromIndex: ", i, ", toIndex: ", i2, ", size: ", iA);
            throw null;
        }
        if (i <= i2) {
            this.d = i2 - i;
        } else {
            dn0.a("fromIndex: ", i, " > toIndex: ", i2);
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1620h0
    public final int a() {
        return this.d;
    }

    @Override // java.util.List
    public final Object get(int i) {
        int i2 = this.d;
        if (i >= 0 && i < i2) {
            return this.b.get(this.c + i);
        }
        rnd.a("index: ", i, ", size: ", i2);
        return null;
    }
}
