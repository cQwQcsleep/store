package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0346z5;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Dq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0443Dq extends AbstractC0469Eq {
    public final C0346z5 e;
    public Cl0 f;

    public C0443Dq(C0346z5 c0346z5, Cl0 cl0) {
        this.e = c0346z5;
        this.f = cl0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0469Eq
    public final void a(Cl0 cl0) {
        this.f = cl0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0469Eq
    public final Cl0 c() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC0469Eq
    public final com.android.tools.r8.graph.I2 d() {
        return this.e.getReference().getType();
    }

    @Override // com.android.tools.r8.internal.AbstractC0469Eq
    public final C0443Dq a() {
        return this;
    }
}
