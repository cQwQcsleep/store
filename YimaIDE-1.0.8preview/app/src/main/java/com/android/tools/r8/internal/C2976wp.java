package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2976wp extends AbstractC0779Qp {
    public final Object c;
    public List d;
    public List e;
    public List f;
    public List g;
    public List h;

    public C2976wp(Object obj) {
        super(589824, null);
        this.c = obj;
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final J2 a(String str, boolean z) {
        D2 d2 = new D2(str);
        if (z) {
            this.d = AbstractC2287ol0.a(this.d, d2);
            return d2;
        }
        this.e = AbstractC2287ol0.a(this.e, d2);
        return d2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final void a() {
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        C2369pj0 c2369pj0 = new C2369pj0(i, c3052xj0, str);
        if (z) {
            this.f = AbstractC2287ol0.a(this.f, c2369pj0);
            return c2369pj0;
        }
        this.g = AbstractC2287ol0.a(this.g, c2369pj0);
        return c2369pj0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final void a(H4 h4) {
        this.h = AbstractC2287ol0.a(this.h, h4);
    }
}
