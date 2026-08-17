package com.android.tools.r8.internal;

import java.util.HashSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RG {
    public final boolean a;
    public final HashSet b = new HashSet();

    public RG(boolean z) {
        this.a = z;
    }

    public final TG a() {
        if (this.b.isEmpty()) {
            if (this.a) {
                return TG.b;
            }
            defpackage.l0.a("Invalid keep options that disallow nothing.");
            return null;
        }
        int size = this.b.size();
        SG[] sgArr = SG.g;
        int length = ((SG[]) sgArr.clone()).length;
        boolean z = this.a;
        if (size == length) {
            if (!z) {
                return TG.b;
            }
            defpackage.l0.a("Invalid keep options that allow everything.");
            return null;
        }
        if (z) {
            return new TG(AbstractC2554rv.a(this.b));
        }
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        for (SG sg : (SG[]) sgArr.clone()) {
            if (!this.b.contains(sg)) {
                c1870jv.a(sg);
            }
        }
        return new TG(c1870jv.a());
    }
}
