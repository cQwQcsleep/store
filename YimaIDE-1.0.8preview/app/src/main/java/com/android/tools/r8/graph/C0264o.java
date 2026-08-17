package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.XR;
import com.android.tools.r8.internal.Yg0;

/* JADX INFO: renamed from: com.android.tools.r8.graph.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0264o implements Yg0 {
    public final /* synthetic */ C0333y a;
    public final /* synthetic */ XR b;
    public final /* synthetic */ AbstractC3148ys c;

    public C0264o(C0333y c0333y, XR xr, AbstractC3148ys abstractC3148ys) {
        this.a = c0333y;
        this.b = xr;
        this.c = abstractC3148ys;
    }

    @Override // com.android.tools.r8.internal.Yg0
    public final void a(Ch0 ch0) {
        C0333y c0333y = this.a;
        c0333y.G = c0333y.G.a(this.b, this.c);
    }

    @Override // com.android.tools.r8.internal.Yg0
    public final boolean b() {
        return this.a.G != null;
    }
}
