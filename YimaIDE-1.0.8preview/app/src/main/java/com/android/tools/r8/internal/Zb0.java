package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Zb0 extends AbstractC1891k8 {
    public final /* synthetic */ PY a;

    public Zb0(PY py) {
        this.a = py;
    }

    @Override // com.android.tools.r8.internal.AbstractC1891k8
    public final boolean a(com.android.tools.r8.graph.B5 b5, com.android.tools.r8.graph.B5 b6) {
        PY py = this.a;
        return py.b.containsKey(py.b(b5));
    }

    @Override // com.android.tools.r8.internal.AbstractC1891k8
    public final boolean b(com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1891k8
    public final void c(com.android.tools.r8.graph.B5 b5) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.AbstractC1891k8
    public final boolean a(com.android.tools.r8.graph.B5 b5) {
        PY py = this.a;
        return py.b.containsKey(py.b(b5));
    }
}
