package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class f extends c {
    public final t b;

    public f(t tVar) {
        this.b = tVar;
    }

    @Override // com.android.tools.r8.utils.structural.AbstractC3519a
    public final int a(I2 i2, I2 i3) {
        if (i2 == i3) {
            return 0;
        }
        I2 i2A = this.b.a(i2);
        I2 i2A2 = this.b.a(i3);
        H2 h2Z0 = i2A.z0();
        H2 h2Z1 = i2A2.z0();
        h2Z0.getClass();
        return h2Z0.a(h2Z1);
    }
}
