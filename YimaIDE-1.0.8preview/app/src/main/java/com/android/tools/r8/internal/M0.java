package com.android.tools.r8.internal;

import defpackage.n33;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class M0 extends G0 {
    public M0(com.android.tools.r8.graph.B1 b1) {
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
            n33.a("Desugared library: cannot parse method ".concat(str));
            return;
        }
        b();
        int iA = a(strArrSplit);
        c(a(strArrSplit[iA]));
        b(a(strArrSplit[iA + 1]));
        a(this.a.c(strArrSplit[iA + 2]));
        for (int i = iA + 3; i < strArrSplit.length; i++) {
            a(a(strArrSplit[i]));
        }
        a();
    }

    public abstract void c(com.android.tools.r8.graph.I2 i2);
}
