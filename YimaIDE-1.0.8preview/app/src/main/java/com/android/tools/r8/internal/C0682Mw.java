package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.dex.code.C0015a2;
import com.android.tools.r8.dex.code.C0020b2;
import com.android.tools.r8.dex.code.C0025c2;
import com.android.tools.r8.dex.code.C0030d2;
import com.android.tools.r8.dex.code.C0035e2;
import com.android.tools.r8.graph.AbstractC0330x3;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.shaking.C3462u;
import defpackage.pah;
import java.util.Arrays;
import java.util.Collections;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Mw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0682Mw extends AbstractC1949kp implements InterfaceC3145yp, InterfaceC2726tw {
    public static final /* synthetic */ boolean k = true;

    public C0682Mw(C0245l1 c0245l1, C2543rl0 c2543rl0, C2543rl0 c2543rl1, boolean z) {
        super(c0245l1, null, Arrays.asList(c2543rl0, c2543rl1));
        if (z) {
            return;
        }
        boolean z2 = k;
        if (!z2) {
            f().a(El0.b);
        }
        if (z2) {
            return;
        }
        value().a(El0.a(c0245l1.i));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        return 15;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        if (k) {
            return 0;
        }
        x01.a("InstancePut instructions define no values.");
        return 0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 30;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean J2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean N1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final InterfaceC3145yp P() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean Q1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final InterfaceC2726tw T() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C0682Mw V() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2543rl0 V0() {
        return f();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        AbstractC0138z1 c0025c2;
        int iA = c2884vl.a(value(), W0());
        int iA2 = c2884vl.a(f(), W0());
        C0245l1 field = getField();
        switch (K2().ordinal()) {
            case 0:
                c0025c2 = new C0025c2(iA, iA2, field);
                break;
            case 1:
                c0025c2 = new com.android.tools.r8.dex.code.Z1(iA, iA2, field);
                break;
            case 2:
                c0025c2 = new C0015a2(iA, iA2, field);
                break;
            case XmlPullParser.END_TAG /* 3 */:
                c0025c2 = new C0020b2(iA, iA2, field);
                break;
            case 4:
                c0025c2 = new C0030d2(iA, iA2, field);
                break;
            case XmlPullParser.CDSECT /* 5 */:
            case XmlPullParser.ENTITY_REF /* 6 */:
                c0025c2 = new com.android.tools.r8.dex.code.Y1(iA, iA2, field);
                break;
            case 7:
            case 8:
                c0025c2 = new C0035e2(iA, iA2, field);
                break;
            default:
                pah.a("Unexpected type: ", K2());
                return;
        }
        c2884vl.a(this, c0025c2);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(C0333y c0333y, com.android.tools.r8.graph.B5 b5, J1 j1, C0864Tw c0864Tw) {
        if (c0333y.g().i()) {
            C0333y<C3403i> c0333yV = c0333y.V();
            C3403i c3403i = (C3403i) c0333yV.g();
            AbstractC0330x3 abstractC0330x3C = c3403i.c(getField());
            if (a(c0333y, b5, c0864Tw, abstractC0330x3C)) {
                return true;
            }
            com.android.tools.r8.graph.F0 f0P = abstractC0330x3C.p();
            if (!k && f0P == null) {
                x01.a("NoSuchFieldError (resolution failure) should be caught.");
                return false;
            }
            if (f0P.getReference().getType().a(c0333yV) || c0333y.e.a(c0333yV, f0P)) {
                return false;
            }
            if (!c3403i.a(f0P) && !a(c0333yV, f0P)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2726tw
    public C2543rl0 f() {
        return (C2543rl0) this.c.get(0);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw, com.android.tools.r8.internal.H
    public final boolean g() {
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC3145yp
    public final int h() {
        return 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        return super.toString() + "; field: " + getField().m0();
    }

    @Override // com.android.tools.r8.internal.AbstractC1949kp, com.android.tools.r8.internal.InterfaceC3145yp
    public C2543rl0 value() {
        return (C2543rl0) this.c.get(1);
    }

    @Override // com.android.tools.r8.internal.InterfaceC3145yp
    public final void a(C2543rl0 c2543rl0) {
        a(1, c2543rl0);
    }

    public static C0682Mw a(C0245l1 c0245l1, C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        return new C0682Mw(c0245l1, c2543rl0, c2543rl1, true);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        if (!abstractC0890Uw.Q1()) {
            return false;
        }
        C0682Mw c0682MwV = abstractC0890Uw.V();
        return c0682MwV.getField() == getField() && c0682MwV.K2() == K2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(AbstractC0890Uw abstractC0890Uw, com.android.tools.r8.ir.regalloc.f fVar, AbstractC2166nO abstractC2166nO) {
        if (!super.a(abstractC0890Uw, fVar, abstractC2166nO)) {
            return false;
        }
        fVar.c().getClass();
        return !value().t().r() || value() == abstractC0890Uw.V().value();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        c0941Wv.a(this);
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return w.a(getField(), b5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.a(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        C0245l1 field = getField();
        C0245l1 field2 = getField();
        C0210g1 c0210g1Q = j8.a.h().c(field2).q();
        if (c0210g1Q != null) {
            field2 = c0210g1Q.getReference();
        }
        j8.a(new C3006x9(field, field2), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.B5 b5, C0333y c0333y, int i, int i3) {
        return AbstractC1244cc.a(this, i2, c0333y, i, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, C2543rl0 c2543rl0) {
        return f() == c2543rl0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C3462u c3462u) {
        c3462u.a((com.android.tools.r8.graph.F2) getField());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        C0245l1 field = getField();
        C2543rl0 c2543rl0F = f();
        C2543rl0 c2543rl0Value = value();
        lk.getClass();
        lk.a(181, Collections.singletonList(field), AbstractC0551Hu.a(c2543rl0F, c2543rl0Value));
    }
}
