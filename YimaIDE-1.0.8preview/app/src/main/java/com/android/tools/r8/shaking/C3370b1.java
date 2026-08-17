package com.android.tools.r8.shaking;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.b1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3370b1 extends AbstractC3410j1 {
    public boolean k;
    public boolean l;

    public C3370b1(C3380d1 c3380d1) {
        super(c3380d1);
        this.k = c3380d1.j;
        this.l = c3380d1.k;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final boolean a(AbstractC3395g1 abstractC3395g1) {
        C3380d1 c3380d1 = (C3380d1) abstractC3395g1;
        return a((AbstractC3420l1) c3380d1) && this.k == c3380d1.j && this.l == c3380d1.k;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final boolean b(AbstractC3395g1 abstractC3395g1) {
        C3380d1 c3380d1 = (C3380d1) abstractC3395g1;
        return a((AbstractC3420l1) c3380d1) && this.k == c3380d1.j && this.l == c3380d1.k;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final AbstractC3395g1 e() {
        return new C3380d1(this);
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final AbstractC3395g1 f() {
        return C3380d1.m;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final AbstractC3395g1 g() {
        return C3380d1.l;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final AbstractC3385e1 i() {
        C3370b1 c3370b1 = (C3370b1) k();
        c3370b1.k = false;
        c3370b1.l = false;
        return c3370b1;
    }

    @Override // com.android.tools.r8.shaking.AbstractC3385e1
    public final AbstractC3385e1 j() {
        return this;
    }

    public C3370b1() {
    }
}
