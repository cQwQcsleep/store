package com.android.tools.r8.internal;

import java.util.HashSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class TX extends ZX {
    public final /* synthetic */ C1069aY b;

    public TX(C1069aY c1069aY) {
        this.b = c1069aY;
    }

    public final C0775Ql a(com.android.tools.r8.graph.I2 i2) {
        com.android.tools.r8.graph.E0 e0D = this.b.b.d(i2);
        return e0D != null ? a(e0D) : new C0775Ql(new HashSet());
    }
}
