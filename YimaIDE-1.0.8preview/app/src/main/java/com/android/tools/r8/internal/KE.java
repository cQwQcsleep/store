package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KE extends EE {
    public KE(HE he) {
        super(he);
    }

    @Override // com.android.tools.r8.internal.EE
    public final KE a() {
        return this;
    }

    @Override // com.android.tools.r8.internal.EE
    public final AbstractC2757uG d() {
        return new NE(this);
    }

    @Override // com.android.tools.r8.internal.EE
    public final String toString() {
        return "class-ref(" + super.toString() + ")";
    }
}
