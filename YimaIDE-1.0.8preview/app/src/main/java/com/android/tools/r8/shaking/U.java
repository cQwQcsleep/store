package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.AbstractC0175b1;
import com.android.tools.r8.graph.C0229j;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class U extends Z {
    public final com.android.tools.r8.graph.I2 a;

    public U(com.android.tools.r8.graph.I2 i2) {
        this.a = i2;
    }

    @Override // com.android.tools.r8.shaking.Z
    public final AbstractC0175b1 a(C0229j c0229j) {
        return c0229j.d(this.a);
    }

    @Override // com.android.tools.r8.shaking.Z
    public final boolean b() {
        return true;
    }

    @Override // com.android.tools.r8.shaking.Z
    public final U a() {
        return this;
    }
}
