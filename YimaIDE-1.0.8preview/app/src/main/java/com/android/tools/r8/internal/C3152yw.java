package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.dex.code.C0094q1;
import com.android.tools.r8.dex.code.C0098r1;
import com.android.tools.r8.dex.code.C0103s1;
import com.android.tools.r8.dex.code.C0108t1;
import com.android.tools.r8.dex.code.C0113u1;
import com.android.tools.r8.dex.code.C0123w1;
import com.android.tools.r8.dex.code.C0128x1;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.shaking.C3462u;
import defpackage.pah;
import java.util.Collections;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3152yw extends AbstractC1949kp implements InterfaceC1694hp, InterfaceC2726tw {
    public static final /* synthetic */ boolean k = true;

    public C3152yw(C0245l1 c0245l1, C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        super(c0245l1, c2543rl0, Collections.singletonList(c2543rl1));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int F2() {
        return 15;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int G2() {
        return 15;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final int H2() {
        return 28;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean J2() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final InterfaceC1694hp N() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final InterfaceC2726tw T() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final C2543rl0 V0() {
        return f();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C2884vl c2884vl) {
        AbstractC0138z1 c0113u1;
        int iA = c2884vl.a(this.b, W0());
        int iA2 = c2884vl.a(f(), W0());
        C0245l1 field = getField();
        switch (K2().ordinal()) {
            case 0:
                c0113u1 = new C0113u1(iA, iA2, field);
                break;
            case 1:
                c0113u1 = new C0098r1(iA, iA2, field);
                break;
            case 2:
                c0113u1 = new C0103s1(iA, iA2, field);
                break;
            case XmlPullParser.END_TAG /* 3 */:
                c0113u1 = new C0108t1(iA, iA2, field);
                break;
            case 4:
                c0113u1 = new C0123w1(iA, iA2, field);
                break;
            case XmlPullParser.CDSECT /* 5 */:
            case XmlPullParser.ENTITY_REF /* 6 */:
                c0113u1 = new C0094q1(iA, iA2, field);
                break;
            case 7:
            case 8:
                c0113u1 = new C0128x1(iA, iA2, field);
                break;
            default:
                pah.a("Unexpected type: ", K2());
                return;
        }
        c2884vl.a(this, c0113u1);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean b(AbstractC0890Uw abstractC0890Uw) {
        abstractC0890Uw.getClass();
        if (!(abstractC0890Uw instanceof C3152yw)) {
            return false;
        }
        C3152yw c3152ywB = abstractC0890Uw.b();
        return c3152ywB.getField() == getField() && c3152ywB.K2() == K2();
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean d(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2726tw
    public final C2543rl0 f() {
        if (k || this.c.size() == 1) {
            return (C2543rl0) this.c.get(0);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw, com.android.tools.r8.internal.H
    public final boolean g() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean g1() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final String toString() {
        return super.toString() + "; field: " + getField().m0();
    }

    @Override // com.android.tools.r8.internal.AbstractC1949kp, com.android.tools.r8.internal.InterfaceC3145yp
    public final C2543rl0 value() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw, com.android.tools.r8.internal.InterfaceC1694hp
    public final C3152yw b() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(Set set) {
        return getField().i.J0();
    }

    public static C3152yw a(C0705Nt c0705Nt, C3152yw c3152yw) {
        C2543rl0 c2543rl0A = c0705Nt.a(c3152yw.a(), c3152yw.k());
        if (!k && c2543rl0A == c3152yw.c()) {
            x1f.a();
            return null;
        }
        C3068xw c3068xw = new C3068xw();
        c3068xw.d = c3152yw.getField();
        c3068xw.e = c3152yw.f();
        c3068xw.a = c2543rl0A;
        C3068xw c3068xw2 = (C3068xw) c3068xw.a();
        return (C3152yw) c3068xw2.a(new C3152yw(c3068xw2.d, c3068xw2.a, c3068xw2.e));
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, C2543rl0 c2543rl0) {
        boolean z = k;
        if (!z && (c2543rl0 == null || !c2543rl0.t().I())) {
            x1f.a();
            return false;
        }
        if (z || this.b != null) {
            return this.b.t().I();
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.ir.optimize.N a(com.android.tools.r8.ir.optimize.W w, com.android.tools.r8.graph.B5 b5) {
        return w.a(getField(), b5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final Object a(C0941Wv c0941Wv) {
        c0941Wv.a(this);
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final AbstractC2624sj0 a(C0333y c0333y) {
        return AbstractC2624sj0.a(getField().i, C2427qS.h(), (C0333y<?>) c0333y);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final com.android.tools.r8.graph.I2 a(C0333y c0333y, Nj0 nj0) {
        return getField().i;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(K5 k5, IL il) {
        il.a(this, k5);
        il.b(this, k5);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(J8 j8) {
        C0245l1 field = getField();
        C0245l1 field2 = getField();
        C0210g1 c0210g1Q = j8.a.h().c(field2).q();
        if (c0210g1Q != null) {
            field2 = c0210g1Q.getReference();
        }
        j8.a(new C2920w9(field, field2), this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5, C2543rl0 c2543rl0) {
        return f() == c2543rl0;
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final boolean a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.B5 b5, C0333y c0333y, int i, int i3) {
        return AbstractC1244cc.a(this, i2, c0333y, i, i3);
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(C3462u c3462u) {
        c3462u.a((com.android.tools.r8.graph.F2) getField());
    }

    @Override // com.android.tools.r8.internal.AbstractC0890Uw
    public final void a(LK lk) {
        C0245l1 field = getField();
        C2543rl0 c2543rl0F = f();
        lk.getClass();
        lk.a(180, Collections.singletonList(field), Collections.singletonList(c2543rl0F));
    }
}
