package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.od, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2270od {
    public static final C2270od c;
    public static final C2270od d;
    public static final C2270od e;
    public static final C2270od f;
    public static final /* synthetic */ boolean g = true;
    public AbstractC2173nV a;
    public AbstractC2173nV b;

    static {
        AbstractC2173nV abstractC2173nV = AbstractC2173nV.a;
        AbstractC2173nV abstractC2173nV2 = AbstractC2173nV.b;
        c = new C2270od(abstractC2173nV, abstractC2173nV2);
        AbstractC2173nV abstractC2173nV3 = AbstractC2173nV.c;
        d = new C2270od(abstractC2173nV3, abstractC2173nV2);
        e = new C2270od(abstractC2173nV2, abstractC2173nV);
        f = new C2270od(abstractC2173nV2, abstractC2173nV3);
    }

    public C2270od(AbstractC2173nV abstractC2173nV, AbstractC2173nV abstractC2173nV2) {
        this.a = abstractC2173nV;
        this.b = abstractC2173nV2;
        if (g || !a()) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final boolean a() {
        return this.a.c() && this.b.c();
    }
}
