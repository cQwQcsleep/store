package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class H1 {
    public static final /* synthetic */ boolean b = true;
    public final C0333y a;

    public H1(C0333y c0333y) {
        this.a = c0333y;
    }

    public final B1 a(B1 b1, B1 b2, AbstractC2624sj0 abstractC2624sj0) {
        F1 f1 = F1.b;
        b1.getClass();
        if ((b1 instanceof C1804j7) || b2.isUnknown()) {
            return b2;
        }
        if (!b1.isUnknown() && !(b2 instanceof C1804j7) && !b1.equals(b2)) {
            boolean z = b1 instanceof K1;
            if (z || (b2 instanceof K1)) {
                if (b || !z || !(b2 instanceof K1)) {
                    return Ak0.a;
                }
                x1f.a();
                return null;
            }
            if (!abstractC2624sj0.I()) {
                boolean z2 = b;
                if (!z2 && !abstractC2624sj0.H()) {
                    x1f.a();
                    return null;
                }
                AbstractC2005lY abstractC2005lYC = abstractC2624sj0.c();
                if (!z2 && (b1 instanceof C2256oS)) {
                    x1f.a();
                    return null;
                }
                if (!z2 && (b2 instanceof C2256oS)) {
                    x1f.a();
                    return null;
                }
                if (f1.a) {
                    abstractC2005lYC.getClass();
                    if (abstractC2005lYC instanceof GA) {
                        if (!b1.z() || !b2.z()) {
                            return Ak0.a;
                        }
                        if (!(b1 instanceof C2525rc0) && (b2 instanceof C2525rc0)) {
                            b2 = b1;
                            b1 = b2;
                        }
                        if (b1 instanceof C2525rc0) {
                            C2525rc0 c2525rc0E = b1.e();
                            if (b2 instanceof C2525rc0) {
                                C2525rc0 c2525rc0E2 = b2.e();
                                return this.a.t.a(((int) c2525rc0E.b) & ((int) c2525rc0E2.b), c2525rc0E.w() & c2525rc0E2.w());
                            }
                            if (!z2 && !(b2 instanceof C1682hi)) {
                                x1f.a();
                                return null;
                            }
                            C1682hi c1682hiL = b2.l();
                            C1 c1 = this.a.t;
                            c1682hiL.getClass();
                            int i = (int) c2525rc0E.b;
                            int iW = c2525rc0E.w();
                            int i2 = c1682hiL.a;
                            return (i2 == i && c1682hiL.b == iW) ? c1682hiL : c1.a(i & i2, iW & c1682hiL.b);
                        }
                        if (!z2 && !(b1 instanceof C1682hi)) {
                            x1f.a();
                            return null;
                        }
                        if (!z2 && !(b2 instanceof C1682hi)) {
                            x1f.a();
                            return null;
                        }
                        C1682hi c1682hiL2 = b1.l();
                        C1682hi c1682hiL3 = b2.l();
                        C1 c2 = this.a.t;
                        c1682hiL2.getClass();
                        int i3 = c1682hiL3.a;
                        int i4 = c1682hiL3.b;
                        int i5 = c1682hiL2.a;
                        return (i5 == i3 && c1682hiL2.b == i4) ? c1682hiL2 : c2.a(i3 & i5, c1682hiL2.b & i4);
                    }
                }
                return Ak0.a;
            }
            if (b1 instanceof C2440qc0) {
                return C2256oS.a(b2);
            }
            if (b2 instanceof C2440qc0) {
                return C2256oS.a(b1);
            }
            if (!(b1 instanceof C2256oS) || !b1.m().a.equals(b2)) {
                return ((b2 instanceof C2256oS) && b2.m().a.equals(b1)) ? b2 : Ak0.a;
            }
        }
        return b1;
    }
}
