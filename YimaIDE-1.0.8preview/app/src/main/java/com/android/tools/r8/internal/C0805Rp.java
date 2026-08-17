package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Rp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0805Rp extends AbstractC0779Qp {
    public final Ag0 c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public L2 i;
    public L2 j;
    public L2 k;
    public L2 l;
    public H4 m;

    public C0805Rp(Ag0 ag0, int i, String str, String str2, String str3, Object obj) {
        super(589824, null);
        this.c = ag0;
        this.d = i;
        this.e = ag0.a(str);
        this.f = ag0.a(str2);
        if (str3 != null) {
            this.g = ag0.a(str3);
        }
        if (obj != null) {
            this.h = ag0.a(obj).a;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final J2 a(String str, boolean z) {
        Ag0 ag0 = this.c;
        if (z) {
            L2 l2A = L2.a(ag0, str, this.i);
            this.i = l2A;
            return l2A;
        }
        L2 l2A2 = L2.a(ag0, str, this.j);
        this.j = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final void a() {
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        Ag0 ag0 = this.c;
        if (z) {
            L2 l2A = L2.a(ag0, i, c3052xj0, str, this.k);
            this.k = l2A;
            return l2A;
        }
        L2 l2A2 = L2.a(ag0, i, c3052xj0, str, this.l);
        this.l = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.AbstractC0779Qp
    public final void a(H4 h4) {
        h4.c = this.m;
        this.m = h4;
    }
}
