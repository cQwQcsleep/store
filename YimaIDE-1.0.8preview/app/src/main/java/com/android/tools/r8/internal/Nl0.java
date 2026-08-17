package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Nl0 extends AbstractC1757ic0 {
    public static final /* synthetic */ boolean c = true;
    public final AbstractC1757ic0 b;

    public Nl0(AbstractC1757ic0 abstractC1757ic0) {
        this.b = abstractC1757ic0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1757ic0
    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        if (c || this.b.a(d2)) {
            return true;
        }
        x01.a("Verification of single class policies failed");
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "VerifySingleClassPolicyAlwaysSatisfied(" + this.b.f() + ")";
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final boolean l() {
        return !C2752uB.b() || this.b.l();
    }
}
