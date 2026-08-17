package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3014xG {
    public static final C3014xG e;
    public final Set a;
    public final AbstractC1484fP b;
    public final AbstractC1484fP c;
    public final AbstractC1484fP d;

    static {
        X40 x40 = X1.f;
        C1230cP c1230cP = AbstractC1484fP.a;
        e = new C3014xG(x40, c1230cP, c1230cP, c1230cP);
    }

    public C3014xG(Set set, AbstractC1484fP abstractC1484fP, AbstractC1484fP abstractC1484fP2, AbstractC1484fP abstractC1484fP3) {
        this.a = set;
        this.b = abstractC1484fP;
        this.c = abstractC1484fP2;
        this.d = abstractC1484fP3;
    }

    public boolean a() {
        if (!X1.a(this.a)) {
            return false;
        }
        AbstractC1484fP abstractC1484fP = this.b;
        abstractC1484fP.getClass();
        if (!(abstractC1484fP instanceof C1230cP)) {
            return false;
        }
        AbstractC1484fP abstractC1484fP2 = this.c;
        abstractC1484fP2.getClass();
        if (!(abstractC1484fP2 instanceof C1230cP)) {
            return false;
        }
        AbstractC1484fP abstractC1484fP3 = this.d;
        abstractC1484fP3.getClass();
        return abstractC1484fP3 instanceof C1230cP;
    }

    public String toString() {
        if (a()) {
            return "*";
        }
        StringBuilder sb = new StringBuilder();
        AbstractC3035xa0.a(new C2949wa0(sb), this);
        return sb.toString();
    }
}
