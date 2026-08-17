package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class U8 extends J9 {
    public final EnumC2211nu c;
    public final El0 d;
    public final K9 e;

    public U8(EnumC2211nu enumC2211nu, El0 el0, K9 k9) {
        this.c = enumC2211nu;
        this.d = el0;
        this.e = k9;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final K9 A() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean C() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean N() {
        return true;
    }

    @Override // com.android.tools.r8.internal.J9
    public final boolean T() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int w() {
        return 3;
    }
}
