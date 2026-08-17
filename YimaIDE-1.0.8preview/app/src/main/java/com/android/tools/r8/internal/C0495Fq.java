package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0495Fq extends AbstractC0469Eq {
    public final com.android.tools.r8.graph.B5 e;
    public final C1676hf f;
    public final int g;
    public final com.android.tools.r8.graph.I2 h;

    public C0495Fq(com.android.tools.r8.graph.B5 b5, C1676hf c1676hf, int i, com.android.tools.r8.graph.I2 i2) {
        this.e = b5;
        this.f = c1676hf;
        this.g = i;
        this.h = i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0469Eq
    public final void a(Cl0 cl0) {
        this.f.a(this.g, cl0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0469Eq
    public final C0495Fq b() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0469Eq
    public final Cl0 c() {
        C1676hf c1676hf = this.f;
        return (Cl0) c1676hf.c.get(this.g);
    }

    @Override // com.android.tools.r8.internal.AbstractC0469Eq
    public final com.android.tools.r8.graph.I2 d() {
        return this.h;
    }
}
