package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ml0 extends NP {
    public static final /* synthetic */ boolean c = true;
    public final NP b;

    public Ml0(NP np) {
        this.b = np;
    }

    @Override // com.android.tools.r8.internal.NP
    public final Collection a(C1868jt c1868jt) {
        boolean z = c;
        if (!z) {
            Collection collectionA = this.b.a(c1868jt);
            if (!z && collectionA.size() != 1) {
                x1f.a();
                return null;
            }
            C1868jt c1868jt2 = (C1868jt) collectionA.iterator().next();
            if (!z && (c1868jt2.b.size() != c1868jt.b.size() || !c1868jt.b.containsAll(c1868jt2))) {
                x1f.a();
                return null;
            }
        }
        return Collections.singletonList(c1868jt);
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "VerifyMultiClassPolicyAlwaysSatisfied(" + this.b.f() + ")";
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final boolean l() {
        return !C2752uB.b() || this.b.l();
    }
}
