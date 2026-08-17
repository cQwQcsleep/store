package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2074mH {
    public AbstractC2757uG a;
    public AbstractC2072mF b = C1986lF.b;

    public final C2159nH a() {
        AbstractC2757uG abstractC2757uG = this.a;
        if (abstractC2757uG != null) {
            return new C2159nH(abstractC2757uG, this.b);
        }
        defpackage.l0.a("Target must define an item pattern");
        return null;
    }

    public final C2074mH a(AbstractC2671tG abstractC2671tG) {
        this.a = abstractC2671tG.f();
        return this;
    }

    public final C2074mH a(AbstractC2072mF abstractC2072mF) {
        this.b = abstractC2072mF;
        return this;
    }

    public final C2074mH a(AbstractC2757uG abstractC2757uG) {
        this.a = abstractC2757uG;
        return this;
    }
}
