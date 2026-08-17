package com.android.tools.r8.naming;

import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y {
    public final /* synthetic */ Z a;

    public Y(Z z) {
        this.a = z;
    }

    public final void a(com.android.tools.r8.graph.H0 h0, H2 h2) {
        if (h2.b(h0.getReference().x0())) {
            return;
        }
        this.a.c.put(h0.getReference(), h2);
    }

    public final I2 a(I2 i2) {
        return (I2) this.a.g.getOrDefault(i2, i2);
    }
}
