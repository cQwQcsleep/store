package com.android.tools.r8.graph;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N5 extends AbstractC0184c3 {
    public static final /* synthetic */ boolean c = true;
    public final H0 b;

    public N5(H0 h0, T4.c cVar) {
        super(cVar);
        if (c || h0 != null) {
            this.b = h0;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0184c3
    public final N5 a() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0184c3
    public final H0 b() {
        return this.b;
    }
}
