package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2073mG {
    public static final /* synthetic */ boolean e = true;
    public AbstractC2515rV a = C2345pV.b;
    public C1901kG b = C1901kG.h;
    public C1987lG c = C1987lG.b;
    public AbstractC2330pG d = C2244oG.b;

    public final C2073mG a(FG fg) {
        if (!e && !fg.g()) {
            x1f.a();
            return null;
        }
        C1901kG c1901kG = C1901kG.h;
        this.b = ((C1816jG) new C1816jG().a(fg.d())).c();
        return this;
    }

    public final C2158nG a() {
        return new C2158nG(this.a, this.b, this.c, this.d);
    }
}
