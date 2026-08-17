package com.android.tools.r8.dex;

import com.android.tools.r8.graph.H2;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.naming.J0;

/* JADX INFO: renamed from: com.android.tools.r8.dex.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0142e extends AbstractC0146i {
    public final /* synthetic */ C1975l7 c;
    public final /* synthetic */ C0148k d;

    public C0142e(C0148k c0148k, C1975l7 c1975l7) {
        this.d = c0148k;
        this.c = c1975l7;
    }

    @Override // com.android.tools.r8.dex.AbstractC0146i
    public final H2 a() {
        return this.d.a((J0) this.c.a());
    }
}
