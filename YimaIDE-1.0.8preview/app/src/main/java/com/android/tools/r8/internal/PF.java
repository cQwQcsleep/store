package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PF extends VF {
    public final InterfaceC1221cG j;
    public final C1475fG k;

    public PF(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG, C1475fG c1475fG) {
        super(c2516rW);
        this.j = interfaceC1221cG;
        this.k = c1475fG;
    }

    @Override // com.android.tools.r8.internal.VF, com.android.tools.r8.internal.J2
    public final void a() {
        super.a();
        this.j.accept(new QE(c()));
    }

    @Override // com.android.tools.r8.internal.VF
    public final C1475fG b() {
        return this.k;
    }
}
