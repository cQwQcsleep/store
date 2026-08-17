package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Oc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0714Oc extends AbstractC2526rd {
    public List c;
    public List d;
    public List e;
    public List f;
    public List g;
    public final ArrayList h;
    public List i;
    public List j;
    public List k;
    public final ArrayList l;
    public final ArrayList m;

    public C0714Oc() {
        super(null);
        new ArrayList();
        this.h = new ArrayList();
        this.l = new ArrayList();
        this.m = new ArrayList();
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final J2 a(String str, boolean z) {
        D2 d2 = new D2(str);
        if (z) {
            this.c = AbstractC2287ol0.a(this.c, d2);
            return d2;
        }
        this.d = AbstractC2287ol0.a(this.d, d2);
        return d2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final AbstractC1966l20 b(String str, String str2, String str3) {
        C1880k20 c1880k20 = new C1880k20();
        this.k = AbstractC2287ol0.a(this.k, c1880k20);
        return c1880k20;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void c(String str) {
        this.j = AbstractC2287ol0.a(this.j, str);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void b(String str) {
        this.i = AbstractC2287ol0.a(this.i, str);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(String str) {
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(String str, String str2) {
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(String str, String str2, String str3) {
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(int i, int i2, String str, String str2, String str3, String[] strArr) {
        AbstractC2287ol0.a(strArr);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final AbstractC2167nP a(int i, String str, String str2) {
        return new C1825jP();
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a() {
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final J2 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        C2369pj0 c2369pj0 = new C2369pj0(i, c3052xj0, str);
        if (z) {
            this.e = AbstractC2287ol0.a(this.e, c2369pj0);
            return c2369pj0;
        }
        this.f = AbstractC2287ol0.a(this.f, c2369pj0);
        return c2369pj0;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(H4 h4) {
        this.g = AbstractC2287ol0.a(this.g, h4);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(int i, String str, String str2, String str3) {
        this.h.add(new C1871jw());
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final AbstractC0779Qp a(int i, String str, String str2, String str3, Object obj) {
        C2976wp c2976wp = new C2976wp(obj);
        this.l.add(c2976wp);
        return c2976wp;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final XO a(int i, String str, String str2, String str3, String[] strArr) {
        C3106yO c3106yO = new C3106yO(589824, i, str, str2, strArr);
        this.m.add(c3106yO);
        return c3106yO;
    }
}
