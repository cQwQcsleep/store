package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2360pf extends AbstractC2445qf {
    public static final /* synthetic */ boolean e = true;
    public AbstractC0439Dm d;

    public C2360pf(AbstractC0439Dm abstractC0439Dm, Set set) {
        super(set);
        this.d = abstractC0439Dm;
        boolean z = e;
        if (!z && r()) {
            x01.a("Must use BottomReceiverParameterState instead");
            throw null;
        }
        if (z || !this.d.l()) {
            return;
        }
        x01.a("Must use UnknownParameterState instead");
        throw null;
    }

    @Override // com.android.tools.r8.internal.AbstractC2445qf
    public final WR a(C0333y c0333y, AbstractC2445qf abstractC2445qf, com.android.tools.r8.graph.I2 i2, Y1 y1) {
        if (!e && i2 != null && !i2.M0()) {
            x1f.a();
            return null;
        }
        AbstractC0439Dm abstractC0439DmT = abstractC2445qf.t();
        AbstractC0439Dm abstractC0439Dm = this.d;
        AbstractC0439Dm abstractC0439DmA = abstractC0439Dm.a(c0333y, abstractC0439DmT);
        if (i2 != null) {
            this.d = Cm0.a(c0333y, abstractC0439DmA, i2, C2427qS.h());
        } else {
            this.d = abstractC0439DmA;
        }
        boolean zEquals = this.d.equals(abstractC0439Dm);
        if (this.d.l()) {
            return Bk0.b;
        }
        boolean zA = a(abstractC2445qf);
        if (b(c0333y)) {
            return Bk0.b;
        }
        if (zEquals && !zA) {
            return this;
        }
        y1.b();
        return this;
    }

    @Override // com.android.tools.r8.internal.Cl0
    public final Cl0 l() {
        return new C2360pf(this.d, n());
    }

    @Override // com.android.tools.r8.internal.AbstractC2530rf
    public final AbstractC1889k7 o() {
        return C1634h7.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC2530rf
    public final boolean r() {
        return this.d.g() && !q();
    }

    @Override // com.android.tools.r8.internal.AbstractC2530rf
    public final boolean s() {
        return this.d.l();
    }

    @Override // com.android.tools.r8.internal.AbstractC2445qf
    public final AbstractC0439Dm t() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC2445qf
    public final C2427qS u() {
        return this.d.d();
    }

    @Override // com.android.tools.r8.internal.Cl0
    public final B1 a(C0333y c0333y) {
        return Ak0.a;
    }
}
