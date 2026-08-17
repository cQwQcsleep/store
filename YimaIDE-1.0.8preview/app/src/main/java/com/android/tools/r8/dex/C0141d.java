package com.android.tools.r8.dex;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.naming.J0;

/* JADX INFO: renamed from: com.android.tools.r8.dex.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0141d extends AbstractC0146i {
    public final /* synthetic */ W c;
    public final /* synthetic */ C1975l7 d;
    public final /* synthetic */ B1 e;

    public C0141d(W w, C1975l7 c1975l7, B1 b1) {
        this.c = w;
        this.d = c1975l7;
        this.e = b1;
    }

    @Override // com.android.tools.r8.dex.AbstractC0146i
    public final H2 a() {
        W w = this.c;
        String str = ((J0) this.d.a()).a;
        if (!W.g && w.a.b.containsKey("pg-map-id")) {
            x1f.a();
            return null;
        }
        w.a.a("pg-map-id", str);
        return this.e.c(this.c.toString());
    }
}
