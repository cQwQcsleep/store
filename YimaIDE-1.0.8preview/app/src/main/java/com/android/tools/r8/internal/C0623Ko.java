package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ko, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0623Ko extends AbstractC0649Lo {
    public static final /* synthetic */ boolean c = true;
    public final com.android.tools.r8.graph.I2 b;

    public C0623Ko(com.android.tools.r8.graph.I2 i2) {
        if (c || i2.U0()) {
            this.b = i2;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0493Fo
    public final com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.B1 b1) {
        if (c || this.b != null) {
            return this.b;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0493Fo
    public final AbstractC2624sj0 a(C0333y c0333y, com.android.tools.r8.graph.I2 i2) {
        return AbstractC2624sj0.a(i2, C2427qS.h(), (C0333y<?>) c0333y);
    }

    @Override // com.android.tools.r8.internal.AbstractC0493Fo
    public final AbstractC1926kc0 a(C0333y c0333y) {
        C1 c1 = c0333y.t;
        com.android.tools.r8.graph.I2 i2 = this.b;
        c1.getClass();
        return C1.b(i2);
    }
}
