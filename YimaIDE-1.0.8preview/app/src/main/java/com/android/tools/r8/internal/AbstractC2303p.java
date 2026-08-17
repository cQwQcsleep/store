package com.android.tools.r8.internal;

import defpackage.n33;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2303p extends G0 {
    public AbstractC2303p(com.android.tools.r8.graph.B1 b1) {
        super(b1);
    }

    public abstract void a();

    public abstract void a(com.android.tools.r8.graph.H2 h2);

    public abstract void a(com.android.tools.r8.graph.I2 i2);

    public abstract void b();

    public abstract void b(com.android.tools.r8.graph.I2 i2);

    public final void b(String str) {
        String[] strArrSplit = str.split("\\s+|,\\s+|#|\\(|\\)");
        if (strArrSplit.length < 3) {
            n33.a("Desugared library: cannot parse field ".concat(str));
            return;
        }
        b();
        int iA = a(strArrSplit);
        a(a(strArrSplit[iA]));
        b(a(strArrSplit[iA + 1]));
        a(this.a.c(strArrSplit[iA + 2]));
        a();
    }
}
