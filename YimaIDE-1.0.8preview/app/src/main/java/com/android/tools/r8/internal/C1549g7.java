package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g7, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1549g7 extends AbstractC1889k7 {
    public static final C1549g7 b = new C1549g7();
    public static final /* synthetic */ boolean c = true;

    @Override // com.android.tools.r8.internal.Cl0
    public final Cl0 a(C0333y c0333y, Cl0 cl0, com.android.tools.r8.graph.I2 i2, AbstractC1589ge0 abstractC1589ge0, Y1 y1) {
        if (cl0.g()) {
            if (c || cl0 == b) {
                return this;
            }
            x1f.a();
            return null;
        }
        if (cl0 instanceof Bk0) {
            return cl0;
        }
        if (c || (cl0 instanceof C2188nf)) {
            return abstractC1589ge0.a(cl0);
        }
        x1f.a();
        return null;
    }
}
