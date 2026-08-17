package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m20, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2052m20 extends AbstractC1966l20 {
    public final Ag0 b;
    public final int c;
    public final int d;
    public final int e;
    public L2 f;
    public L2 g;
    public L2 h;
    public L2 i;
    public H4 j;

    public C2052m20(Ag0 ag0, String str, String str2, String str3) {
        super(589824, null);
        this.b = ag0;
        this.c = ag0.a(str);
        this.d = ag0.a(str2);
        if (str3 != null) {
            this.e = ag0.a(str3);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final J2 a(String str, boolean z) {
        Ag0 ag0 = this.b;
        if (z) {
            L2 l2A = L2.a(ag0, str, this.f);
            this.f = l2A;
            return l2A;
        }
        L2 l2A2 = L2.a(ag0, str, this.g);
        this.g = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final void a() {
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        Ag0 ag0 = this.b;
        if (z) {
            L2 l2A = L2.a(ag0, i, c3052xj0, str, this.h);
            this.h = l2A;
            return l2A;
        }
        L2 l2A2 = L2.a(ag0, i, c3052xj0, str, this.i);
        this.i = l2A2;
        return l2A2;
    }

    @Override // com.android.tools.r8.internal.AbstractC1966l20
    public final void a(H4 h4) {
        h4.c = this.j;
        this.j = h4;
    }
}
