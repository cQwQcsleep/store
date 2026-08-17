package com.android.tools.r8.shaking;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.j1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3410j1 extends AbstractC3385e1 {
    public boolean j;

    public AbstractC3410j1(AbstractC3420l1 abstractC3420l1) {
        super(abstractC3420l1);
        this.j = abstractC3420l1.i;
    }

    public final boolean a(AbstractC3420l1 abstractC3420l1) {
        return super.a((AbstractC3395g1) abstractC3420l1) && this.j == abstractC3420l1.i;
    }

    public final AbstractC3410j1 k() {
        this.b = false;
        j();
        this.c = false;
        j();
        this.d = false;
        j();
        b();
        c();
        d();
        this.h = false;
        j();
        this.i = false;
        j();
        AbstractC3410j1 abstractC3410j1 = (AbstractC3410j1) j();
        abstractC3410j1.j = false;
        return (AbstractC3410j1) abstractC3410j1.j();
    }

    public AbstractC3410j1() {
    }
}
