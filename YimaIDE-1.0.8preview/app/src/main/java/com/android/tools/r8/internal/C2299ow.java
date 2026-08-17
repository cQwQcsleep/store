package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ow, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2299ow implements InterfaceC2385pw {
    public static final /* synthetic */ boolean b = true;
    public final int a;

    public C2299ow(int i) {
        this.a = i;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2385pw
    public final InterfaceC2385pw a(com.android.tools.r8.graph.proto.c cVar) {
        com.android.tools.r8.graph.proto.b bVarA = cVar.a(this.a);
        if (!bVarA.c()) {
            int iB = cVar.b(this.a);
            return iB != this.a ? new C2299ow(iB) : this;
        }
        com.android.tools.r8.graph.proto.g gVarA = bVarA.a();
        if (gVarA.g()) {
            return gVarA.b;
        }
        if (b) {
            return C2797uk0.a;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2385pw
    public final C2299ow f() {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2385pw
    public final boolean h() {
        return true;
    }

    public final String toString() {
        return "InstanceFieldArgumentInitializationInfo(argumentIndex=" + this.a + ")";
    }

    @Override // com.android.tools.r8.internal.InterfaceC2385pw
    /* JADX INFO: renamed from: a */
    public final InterfaceC2385pw b(C0333y c0333y, com.android.tools.r8.graph.I2 i2, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        return this;
    }
}
