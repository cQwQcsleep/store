package com.android.tools.r8.shaking;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.d1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3380d1 extends AbstractC3420l1 {
    public static final C3380d1 l;
    public static final C3380d1 m;
    public static final /* synthetic */ boolean n = true;
    public final boolean j;
    public final boolean k;

    static {
        C3370b1 c3370b1 = (C3370b1) new C3370b1().k();
        c3370b1.k = false;
        c3370b1.l = false;
        l = (C3380d1) c3370b1.a();
        AbstractC3410j1 abstractC3410j1 = (AbstractC3410j1) new C3370b1().h();
        abstractC3410j1.j = true;
        C3370b1 c3370b2 = (C3370b1) ((AbstractC3410j1) abstractC3410j1.j());
        c3370b2.k = true;
        c3370b2.l = true;
        m = (C3380d1) c3370b2.a();
    }

    public C3380d1(C3370b1 c3370b1) {
        super(c3370b1);
        this.j = c3370b1.k;
        this.k = c3370b1.l;
    }

    public static C3375c1 b() {
        return m.a();
    }

    public final C3375c1 a() {
        if (n || this != l) {
            return new C3375c1(this);
        }
        x1f.a();
        return null;
    }
}
