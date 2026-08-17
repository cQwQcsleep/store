package com.android.tools.r8.graph;

import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.XR;
import com.android.tools.r8.internal.Yg0;

/* JADX INFO: renamed from: com.android.tools.r8.graph.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0312v implements Yg0 {
    public final /* synthetic */ C0333y a;
    public final /* synthetic */ XR b;

    public C0312v(C0333y c0333y, XR xr) {
        this.a = c0333y;
        this.b = xr;
    }

    @Override // com.android.tools.r8.internal.Yg0
    public final void a(Ch0 ch0) {
        C0333y c0333y = this.a;
        c0333y.a(c0333y.p.a(this.b, ch0));
    }

    @Override // com.android.tools.r8.internal.Yg0
    public final boolean b() {
        return this.a.p != null;
    }
}
