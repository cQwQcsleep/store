package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class JQ extends AbstractC0708Nw {
    public com.android.tools.r8.graph.I2 d;

    public final JQ a(com.android.tools.r8.graph.I2 i2) {
        this.d = i2;
        return this;
    }

    public final KQ c() {
        return (KQ) a(new KQ(this.d, this.a));
    }

    @Override // com.android.tools.r8.internal.AbstractC0708Nw
    public final AbstractC0708Nw a() {
        return this;
    }
}
