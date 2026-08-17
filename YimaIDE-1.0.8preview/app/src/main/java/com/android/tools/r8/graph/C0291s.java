package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.XR;
import com.android.tools.r8.internal.Yg0;

/* JADX INFO: renamed from: com.android.tools.r8.graph.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0291s implements Yg0 {
    public final /* synthetic */ C0333y a;
    public final /* synthetic */ XR b;
    public final /* synthetic */ AbstractC3148ys c;

    public C0291s(C0333y c0333y, XR xr, AbstractC3148ys abstractC3148ys) {
        this.a = c0333y;
        this.b = xr;
        this.c = abstractC3148ys;
    }

    @Override // com.android.tools.r8.internal.Yg0
    public final void a(Ch0 ch0) {
        C0333y c0333y = this.a;
        c0333y.e = c0333y.e.a(c0333y, this.b, this.c, ch0);
    }

    @Override // com.android.tools.r8.internal.Yg0
    public final boolean b() {
        return !this.a.e.a.isEmpty();
    }
}
