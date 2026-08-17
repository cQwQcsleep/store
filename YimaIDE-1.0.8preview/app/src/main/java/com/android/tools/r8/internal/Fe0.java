package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.ir.optimize.C3242a;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Fe0 implements Me0 {
    public static final /* synthetic */ boolean b = true;
    public final String a;

    public Fe0(String str) {
        this.a = str;
    }

    @Override // com.android.tools.r8.internal.Me0
    public final void a(C0333y c0333y, C0705Nt c0705Nt, InterfaceC0968Xw interfaceC0968Xw, AbstractC0890Uw abstractC0890Uw, C3242a c3242a, InterfaceC2702tf0 interfaceC2702tf0) {
        C0322w2 c0322w2;
        C0322w2 c0322w3;
        AbstractC1133bC abstractC1133bCC0 = interfaceC0968Xw.previous().c0();
        boolean z = b;
        if (!z && abstractC1133bCC0 == null) {
            x1f.a();
            return;
        }
        C2543rl0 c2543rl0A = Me0.a(c0333y, c0705Nt, interfaceC0968Xw, abstractC1133bCC0, this.a);
        interfaceC0968Xw.next();
        C0322w2 c0322w2U2 = abstractC1133bCC0.U2();
        if (abstractC1133bCC0.a(c0333y.a())) {
            boolean z2 = VB.n;
            UB ub = (UB) new UB().a(AbstractC0551Hu.a(abstractC1133bCC0.V2(), c2543rl0A));
            com.android.tools.r8.graph.B1 b1A = c0333y.a();
            if (c0322w2U2.w0() == b1A.y2) {
                c0322w3 = b1A.g4.q;
            } else {
                if (!Ee0.a && c0322w2U2.w0() != b1A.x2) {
                    x1f.a();
                    return;
                }
                c0322w3 = b1A.f4.q;
            }
            ub.d = c0322w3;
            ub.a = abstractC1133bCC0.c();
            interfaceC0968Xw.e(ub.c());
            return;
        }
        com.android.tools.r8.graph.B1 b1A2 = c0333y.a();
        if (c0322w2U2 == b1A2.g4.l || c0322w2U2 == b1A2.f4.l) {
            abstractC1133bCC0.a(1, c2543rl0A);
            return;
        }
        boolean z3 = C2496rC.m;
        C2412qC c2412qC = (C2412qC) new C2412qC().a(AbstractC0551Hu.a(abstractC1133bCC0.V2(), c2543rl0A));
        com.android.tools.r8.graph.B1 b1A3 = c0333y.a();
        if (c0322w2U2.w0() == b1A3.y2) {
            c0322w2 = b1A3.g4.l;
        } else {
            if (!z && c0322w2U2.w0() != b1A3.x2) {
                x1f.a();
                return;
            }
            c0322w2 = b1A3.f4.l;
        }
        c2412qC.d = c0322w2;
        c2412qC.a = abstractC1133bCC0.c();
        interfaceC0968Xw.e(c2412qC.c());
    }

    @Override // com.android.tools.r8.internal.Me0
    public final boolean b() {
        return true;
    }
}
