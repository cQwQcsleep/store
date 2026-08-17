package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ML extends C2369pj0 {
    public final ArrayList f;
    public final ArrayList g;
    public final ArrayList h;

    public ML(int i, C3052xj0 c3052xj0, XI[] xiArr, XI[] xiArr2, int[] iArr, String str) {
        super(i, c3052xj0, str, 0);
        this.f = AbstractC2287ol0.a(xiArr);
        this.g = AbstractC2287ol0.a(xiArr2);
        this.h = AbstractC2287ol0.a(iArr);
    }

    public final void a(XO xo, boolean z) {
        int size = this.f.size();
        WI[] wiArr = new WI[size];
        WI[] wiArr2 = new WI[this.g.size()];
        int[] iArr = new int[this.h.size()];
        for (int i = 0; i < size; i++) {
            wiArr[i] = ((XI) this.f.get(i)).b();
            wiArr2[i] = ((XI) this.g.get(i)).b();
            iArr[i] = ((Integer) this.h.get(i)).intValue();
        }
        a(xo.a(this.d, this.e, wiArr, wiArr2, iArr, this.b, z));
    }
}
