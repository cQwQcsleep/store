package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1783iu extends AbstractC3148ys {
    public static final C1783iu b = new C1783iu();
    public static final /* synthetic */ boolean c = true;

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final C2850vO a(C0322w2 c0322w2, C0322w2 c0322w3, EnumC2326pC enumC2326pC, AbstractC3148ys abstractC3148ys) {
        if (c || abstractC3148ys == null || abstractC3148ys.l()) {
            return new C2850vO(c0322w2, null, enumC2326pC, com.android.tools.r8.graph.proto.j.d).a(this);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final Iterable b(com.android.tools.r8.graph.I2 i2) {
        return C2753uC.b(i2);
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final com.android.tools.r8.graph.proto.j e(AbstractC3148ys abstractC3148ys, C0322w2 c0322w2) {
        return com.android.tools.r8.graph.proto.j.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final boolean l() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final boolean n() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    /* JADX INFO: renamed from: e */
    public final com.android.tools.r8.graph.I2 c(AbstractC3148ys abstractC3148ys, com.android.tools.r8.graph.I2 i2) {
        return i2;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final boolean b(AbstractC3148ys abstractC3148ys) {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final boolean a(AbstractC3148ys abstractC3148ys) {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final String a(String str) {
        return str;
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final C2035lp a(C0245l1 c0245l1, AbstractC3148ys abstractC3148ys, InterfaceC2979ws interfaceC2979ws) {
        return interfaceC2979ws.a(new C2035lp(c0245l1, null, null, null));
    }

    @Override // com.android.tools.r8.internal.AbstractC3148ys
    public final C2850vO a(C0322w2 c0322w2, C0322w2 c0322w3, EnumC2326pC enumC2326pC, AbstractC3148ys abstractC3148ys, InterfaceC3064xs interfaceC3064xs) {
        return interfaceC3064xs.a(a(c0322w2, c0322w3, enumC2326pC, abstractC3148ys));
    }
}
