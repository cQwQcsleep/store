package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.df, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1334df extends AbstractC2445qf {
    public static final /* synthetic */ boolean e = true;
    public C2427qS d;

    public C1334df(C2427qS c2427qS, Set set) {
        super(set);
        this.d = c2427qS;
        boolean z = e;
        if (!z && r()) {
            x01.a("Must use BottomArrayTypeParameterState instead");
            throw null;
        }
        if (z || !this.d.f()) {
            return;
        }
        x01.a("Must use UnknownParameterState instead");
        throw null;
    }

    @Override // com.android.tools.r8.internal.AbstractC2445qf
    public final WR a(C0333y c0333y, AbstractC2445qf abstractC2445qf, com.android.tools.r8.graph.I2 i2, Y1 y1) {
        if (!e && !i2.I0()) {
            x1f.a();
            return null;
        }
        C2427qS c2427qSU = abstractC2445qf.u();
        C2427qS c2427qS = this.d;
        C2427qS c2427qSA = c2427qS.a(c2427qSU);
        this.d = c2427qSA;
        boolean z = c2427qSA != c2427qS;
        if (c2427qSA.f()) {
            return Bk0.b;
        }
        boolean zA = a(abstractC2445qf);
        if (b(c0333y)) {
            return Bk0.b;
        }
        if (!z && !zA) {
            return this;
        }
        y1.b();
        return this;
    }

    @Override // com.android.tools.r8.internal.Cl0
    public final Cl0 l() {
        return new C1334df(this.d, n());
    }

    @Override // com.android.tools.r8.internal.AbstractC2530rf
    public final AbstractC1889k7 o() {
        return Z6.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC2530rf
    public final boolean r() {
        C2427qS c2427qS = this.d;
        c2427qS.getClass();
        return c2427qS == C2427qS.d && !q();
    }

    @Override // com.android.tools.r8.internal.AbstractC2530rf
    public final boolean s() {
        return this.d.f();
    }

    @Override // com.android.tools.r8.internal.AbstractC2445qf
    public final AbstractC0439Dm t() {
        return AbstractC0439Dm.m();
    }

    @Override // com.android.tools.r8.internal.AbstractC2445qf
    public final C2427qS u() {
        return this.d;
    }

    public static WR a(C2427qS c2427qS) {
        if (c2427qS.f()) {
            return Bk0.b;
        }
        return new C1334df(c2427qS, Collections.EMPTY_SET);
    }

    @Override // com.android.tools.r8.internal.Cl0
    public final B1 a(C0333y c0333y) {
        if (this.d.e()) {
            c0333y.t.getClass();
            return C2440qc0.b;
        }
        return Ak0.a;
    }

    @Override // com.android.tools.r8.internal.Cl0
    public final C1334df a() {
        return this;
    }
}
