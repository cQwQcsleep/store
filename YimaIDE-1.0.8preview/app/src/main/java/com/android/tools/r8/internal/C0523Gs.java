package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Gs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0523Gs {
    public final WI a;
    public final WI b;
    public final WI c;
    public final int d;
    public final String e;
    public C0523Gs f;

    public C0523Gs(WI wi, WI wi2, WI wi3, int i, String str) {
        this.a = wi;
        this.b = wi2;
        this.c = wi3;
        this.d = i;
        this.e = str;
    }

    public static C0523Gs a(C0523Gs c0523Gs, WI wi, WI wi2) {
        if (c0523Gs == null) {
            return null;
        }
        C0523Gs c0523GsA = a(c0523Gs.f, wi, wi2);
        c0523Gs.f = c0523GsA;
        WI wi3 = c0523Gs.a;
        int i = wi3.e;
        WI wi4 = c0523Gs.b;
        int i2 = wi4.e;
        int i3 = wi.e;
        int i4 = wi2 == null ? Integer.MAX_VALUE : wi2.e;
        if (i3 >= i2 || i4 <= i) {
            return c0523Gs;
        }
        if (i3 <= i) {
            if (i4 >= i2) {
                return c0523GsA;
            }
            C0523Gs c0523Gs2 = new C0523Gs(wi2, wi4, c0523Gs.c, c0523Gs.d, c0523Gs.e);
            c0523Gs2.f = c0523GsA;
            return c0523Gs2;
        }
        WI wi5 = c0523Gs.c;
        if (i4 >= i2) {
            C0523Gs c0523Gs3 = new C0523Gs(wi3, wi, wi5, c0523Gs.d, c0523Gs.e);
            c0523Gs3.f = c0523GsA;
            return c0523Gs3;
        }
        int i5 = c0523Gs.d;
        String str = c0523Gs.e;
        C0523Gs c0523Gs4 = new C0523Gs(wi2, wi4, wi5, i5, str);
        c0523Gs4.f = c0523GsA;
        c0523Gs.f = c0523Gs4;
        C0523Gs c0523Gs5 = new C0523Gs(wi3, wi, wi5, i5, str);
        c0523Gs5.f = c0523Gs4;
        return c0523Gs5;
    }
}
