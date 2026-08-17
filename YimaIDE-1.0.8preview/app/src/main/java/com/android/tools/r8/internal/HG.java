package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HG extends C3014xG {
    public static final HG k;
    public final AbstractC1484fP f;
    public final AbstractC1484fP g;
    public final AbstractC1484fP h;
    public final AbstractC1484fP i;
    public final AbstractC1484fP j;

    static {
        X40 x40 = X1.f;
        C1230cP c1230cP = AbstractC1484fP.a;
        k = new HG(x40, c1230cP, c1230cP, c1230cP, c1230cP, c1230cP, c1230cP, c1230cP, c1230cP);
    }

    public HG(Set set, AbstractC1484fP abstractC1484fP, AbstractC1484fP abstractC1484fP2, AbstractC1484fP abstractC1484fP3, AbstractC1484fP abstractC1484fP4, AbstractC1484fP abstractC1484fP5, AbstractC1484fP abstractC1484fP6, AbstractC1484fP abstractC1484fP7, AbstractC1484fP abstractC1484fP8) {
        super(set, abstractC1484fP, abstractC1484fP2, abstractC1484fP7);
        this.f = abstractC1484fP3;
        this.g = abstractC1484fP4;
        this.h = abstractC1484fP5;
        this.i = abstractC1484fP6;
        this.j = abstractC1484fP8;
    }

    @Override // com.android.tools.r8.internal.C3014xG
    public final boolean a() {
        if (!super.a()) {
            return false;
        }
        AbstractC1484fP abstractC1484fP = this.f;
        abstractC1484fP.getClass();
        if (!(abstractC1484fP instanceof C1230cP)) {
            return false;
        }
        AbstractC1484fP abstractC1484fP2 = this.g;
        abstractC1484fP2.getClass();
        if (!(abstractC1484fP2 instanceof C1230cP)) {
            return false;
        }
        AbstractC1484fP abstractC1484fP3 = this.h;
        abstractC1484fP3.getClass();
        if (!(abstractC1484fP3 instanceof C1230cP)) {
            return false;
        }
        AbstractC1484fP abstractC1484fP4 = this.i;
        abstractC1484fP4.getClass();
        if (!(abstractC1484fP4 instanceof C1230cP)) {
            return false;
        }
        AbstractC1484fP abstractC1484fP5 = this.j;
        abstractC1484fP5.getClass();
        return abstractC1484fP5 instanceof C1230cP;
    }

    @Override // com.android.tools.r8.internal.C3014xG
    public final String toString() {
        if (a()) {
            return "*";
        }
        StringBuilder sb = new StringBuilder();
        C2949wa0 c2949wa0 = new C2949wa0(sb);
        AbstractC3035xa0.a(c2949wa0, this);
        AbstractC3035xa0.a(c2949wa0, this.f, "synchronized");
        AbstractC3035xa0.a(c2949wa0, this.g, "bridge");
        AbstractC3035xa0.a(c2949wa0, this.h, "native");
        AbstractC3035xa0.a(c2949wa0, this.i, "abstract");
        AbstractC3035xa0.a(c2949wa0, this.j, "strictfp");
        return sb.toString();
    }
}
