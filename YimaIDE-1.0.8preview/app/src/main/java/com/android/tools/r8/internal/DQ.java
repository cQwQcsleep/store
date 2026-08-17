package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.IdentityHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class DQ extends CQ {
    public static final /* synthetic */ boolean o = true;
    public com.android.tools.r8.ir.optimize.A n;

    public DQ(C0333y c0333y, InterfaceC1037a6 interfaceC1037a6, com.android.tools.r8.ir.optimize.A a) {
        super(c0333y, CQ.j, interfaceC1037a6.e(), CQ.l, interfaceC1037a6);
        this.n = a;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final com.android.tools.r8.ir.optimize.A f() {
        if (o || this.n != null) {
            return this.n;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final boolean h() {
        return true;
    }

    public DQ(C0333y c0333y, C1291d6 c1291d6, IdentityHashMap identityHashMap, Z5 z5, C1207c6 c1207c6) {
        super(c0333y, c1291d6, identityHashMap, z5, c1207c6);
    }
}
