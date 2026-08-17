package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Cb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0402Cb extends AbstractC0708Nw {
    public com.android.tools.r8.graph.I2 d;
    public C2543rl0 e;

    public final C0402Cb a(com.android.tools.r8.graph.I2 i2) {
        this.d = i2;
        return this;
    }

    public final C0402Cb b(C2543rl0 c2543rl0) {
        this.e = c2543rl0;
        return this;
    }

    public C0428Db c() {
        return (C0428Db) a(new C0428Db(this.a, this.e, this.d));
    }

    @Override // com.android.tools.r8.internal.AbstractC0708Nw
    public final AbstractC0708Nw a() {
        return this;
    }
}
