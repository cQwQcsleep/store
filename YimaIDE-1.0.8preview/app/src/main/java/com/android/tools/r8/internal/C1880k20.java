package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.k20, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1880k20 extends AbstractC1966l20 {
    public List b;
    public List c;
    public List d;
    public List e;
    public List f;

    public C1880k20() {
        super(589824, null);
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final J2 a(String str, boolean z) {
        D2 d2 = new D2(str);
        if (z) {
            this.b = AbstractC2287ol0.a(this.b, d2);
            return d2;
        }
        this.c = AbstractC2287ol0.a(this.c, d2);
        return d2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final void a() {
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        C2369pj0 c2369pj0 = new C2369pj0(i, c3052xj0, str);
        if (z) {
            this.d = AbstractC2287ol0.a(this.d, c2369pj0);
            return c2369pj0;
        }
        this.e = AbstractC2287ol0.a(this.e, c2369pj0);
        return c2369pj0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final void a(H4 h4) {
        this.f = AbstractC2287ol0.a(this.f, h4);
    }
}
