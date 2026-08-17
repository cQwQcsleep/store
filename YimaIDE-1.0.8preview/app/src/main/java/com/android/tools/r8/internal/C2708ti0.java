package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ti0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2708ti0 {
    public final XI a;
    public final XI b;
    public final XI c;
    public final String d;
    public List e;
    public List f;

    public C2708ti0(XI xi, XI xi2, XI xi3, String str) {
        this.a = xi;
        this.b = xi2;
        this.c = xi3;
        this.d = str;
    }

    public final void a(XO xo) {
        WI wiB = this.a.b();
        WI wiB2 = this.b.b();
        XI xi = this.c;
        xo.a(wiB, wiB2, xi == null ? null : xi.b(), this.d);
        List list = this.e;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                C2369pj0 c2369pj0 = (C2369pj0) this.e.get(i);
                c2369pj0.a(xo.b(c2369pj0.d, c2369pj0.e, c2369pj0.b, true));
            }
        }
        List list2 = this.f;
        if (list2 != null) {
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                C2369pj0 c2369pj1 = (C2369pj0) this.f.get(i2);
                c2369pj1.a(xo.b(c2369pj1.d, c2369pj1.e, c2369pj1.b, false));
            }
        }
    }
}
