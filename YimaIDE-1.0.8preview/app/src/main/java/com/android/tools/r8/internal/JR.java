package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.internal.JR;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class JR<N extends JR<N>> {
    public final com.android.tools.r8.graph.B5 b;

    public JR(com.android.tools.r8.graph.B5 b5) {
        this.b = b5;
    }

    public com.android.tools.r8.graph.B5 a() {
        return this.b;
    }

    public abstract void a(JR jr);

    public abstract void a(JR jr, boolean z);

    public C0231j1 d() {
        return a().e();
    }
}
