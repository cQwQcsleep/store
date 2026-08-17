package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2112mk extends AbstractC2027lk {
    public final C2623sj b;
    public final String c;
    public final C1941kk d;

    public C2112mk(C2623sj c2623sj, C1941kk c1941kk, C2284ok c2284ok) throws C1091ak {
        this.b = c2623sj;
        this.d = c1941kk;
        this.c = c2284ok.c + '.' + c2623sj.l();
        c1941kk.h.a(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final C1941kk b() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String c() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String d() {
        return this.b.l();
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final J0 e() {
        return this.b;
    }
}
