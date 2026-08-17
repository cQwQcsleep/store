package com.android.tools.r8.naming;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;

/* JADX INFO: renamed from: com.android.tools.r8.naming.q0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3344q0 extends AbstractC3345r0 {
    public static final /* synthetic */ boolean c = true;
    public final B1 b;

    public AbstractC3344q0(B1 b1) {
        this.b = b1;
    }

    @Override // com.android.tools.r8.naming.AbstractC3345r0
    public final H2 c(I2 i2) {
        if (i2.T0() || i2.W0() || i2.R0()) {
            return i2.z0();
        }
        if (i2.I0()) {
            return c(i2.a(this.b)).a(i2.C0(), this.b);
        }
        if (c || i2.M0()) {
            return b(i2);
        }
        x1f.a();
        return null;
    }
}
