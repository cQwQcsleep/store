package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1901kG extends C3014xG {
    public static final C1901kG h;
    public final AbstractC1484fP f;
    public final AbstractC1484fP g;

    static {
        X40 x40 = X1.f;
        C1230cP c1230cP = AbstractC1484fP.a;
        h = new C1901kG(x40, c1230cP, c1230cP, c1230cP, c1230cP, c1230cP);
    }

    public C1901kG(Set set, AbstractC1484fP abstractC1484fP, AbstractC1484fP abstractC1484fP2, AbstractC1484fP abstractC1484fP3, AbstractC1484fP abstractC1484fP4, AbstractC1484fP abstractC1484fP5) {
        super(set, abstractC1484fP, abstractC1484fP2, abstractC1484fP5);
        this.f = abstractC1484fP3;
        this.g = abstractC1484fP4;
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
        return abstractC1484fP2 instanceof C1230cP;
    }

    @Override // com.android.tools.r8.internal.C3014xG
    public final String toString() {
        if (a()) {
            return "*";
        }
        StringBuilder sb = new StringBuilder();
        C2949wa0 c2949wa0 = new C2949wa0(sb);
        AbstractC3035xa0.a(c2949wa0, this);
        AbstractC3035xa0.a(c2949wa0, this.f, "volatile");
        AbstractC3035xa0.a(c2949wa0, this.g, "transient");
        return sb.toString();
    }
}
