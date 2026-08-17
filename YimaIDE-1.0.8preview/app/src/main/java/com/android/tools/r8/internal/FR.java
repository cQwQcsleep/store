package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FR extends AbstractC1757ic0 {
    public final C0333y b;
    public final Set c;

    public FR(C0333y c0333y) {
        this.b = c0333y;
        this.c = c0333y.c.b();
    }

    @Override // com.android.tools.r8.internal.AbstractC1757ic0
    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        return (this.b.c.a().contains(d2.getType()) || this.c.contains(d2.getType())) ? false : true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "NoServiceLoaders";
    }
}
