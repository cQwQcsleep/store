package com.android.tools.r8.graph;

/* JADX INFO: renamed from: com.android.tools.r8.graph.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0195e0 extends H0 implements InterfaceC0181c0 {
    public static final /* synthetic */ boolean f = true;

    public C0195e0(I0 i0, C0231j1 c0231j1) {
        super(i0, c0231j1);
    }

    @Override // com.android.tools.r8.graph.G0, com.android.tools.r8.graph.InterfaceC0331x4, com.android.tools.r8.graph.A5
    public final E0 a() {
        E0 e0 = this.b;
        if (!f) {
            e0.getClass();
            if (!(e0 instanceof I0)) {
                x1f.a();
                return null;
            }
        }
        return e0.m();
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final C0195e0 i0() {
        return this;
    }
}
