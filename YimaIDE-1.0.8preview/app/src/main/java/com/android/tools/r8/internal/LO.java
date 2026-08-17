package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class LO extends IO {
    public UY a;
    public RY b;

    public LO() {
        SY sy = UY.d;
        this.b = new RY();
    }

    @Override // com.android.tools.r8.internal.IO
    public boolean a(com.android.tools.r8.graph.B5 b5) {
        UY uy = this.a;
        return uy != null && uy.b.containsKey(b5.getReference());
    }

    @Override // com.android.tools.r8.internal.IO
    public void b(com.android.tools.r8.graph.B5 b5) {
        this.b.add(b5);
    }

    @Override // com.android.tools.r8.internal.IO
    public AbstractC1891k8 c() {
        return C1806j8.a;
    }

    public final void g() {
        if (this.b.b.isEmpty()) {
            this.a = UY.d;
            return;
        }
        this.a = this.b;
        SY sy = UY.d;
        this.b = new RY();
    }
}
