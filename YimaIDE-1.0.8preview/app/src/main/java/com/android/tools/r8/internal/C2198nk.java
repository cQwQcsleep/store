package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2198nk extends AbstractC2027lk {
    public final int b;
    public final C3135yj c;
    public final String d;
    public final C1941kk e;
    public final C0955Xj f;
    public int g = 0;
    public C1856jk[] h;

    public C2198nk(C3135yj c3135yj, C1941kk c1941kk, C0955Xj c0955Xj, int i) {
        this.c = c3135yj;
        this.d = AbstractC2370pk.a(c1941kk, c0955Xj, c3135yj.k());
        this.e = c1941kk;
        this.b = i;
        this.f = c0955Xj;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final C1941kk b() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String c() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String d() {
        return this.c.k();
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final J0 e() {
        return this.c;
    }

    public final boolean f() {
        C1856jk[] c1856jkArr = this.h;
        return c1856jkArr.length == 1 && c1856jkArr[0].g;
    }
}
