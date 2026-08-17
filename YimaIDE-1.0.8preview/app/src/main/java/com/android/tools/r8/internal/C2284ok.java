package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ok, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2284ok extends AbstractC2027lk {
    public final C0462Ej b;
    public final String c;
    public final C1941kk d;
    public final C2112mk[] e;

    public C2284ok(C0462Ej c0462Ej, C1941kk c1941kk) throws C1091ak {
        this.b = c0462Ej;
        this.c = AbstractC2370pk.a(c1941kk, null, c0462Ej.k());
        this.d = c1941kk;
        this.e = new C2112mk[c0462Ej.g.size()];
        for (int i = 0; i < c0462Ej.g.size(); i++) {
            this.e[i] = new C2112mk((C2623sj) c0462Ej.g.get(i), c1941kk, this);
        }
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
        return this.b.k();
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final J0 e() {
        return this.b;
    }
}
