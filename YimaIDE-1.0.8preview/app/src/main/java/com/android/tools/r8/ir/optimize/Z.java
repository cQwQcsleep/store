package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.C2680tP;
import java.util.HashSet;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z {
    public final HashSet a = new HashSet();
    public final com.android.tools.r8.ir.regalloc.f b;

    public Z(com.android.tools.r8.ir.regalloc.f fVar) {
        this.b = fVar;
    }

    public final boolean a(AbstractC0890Uw abstractC0890Uw) {
        if (abstractC0890Uw.i2()) {
            C2680tP c2680tPM0 = abstractC0890Uw.m0();
            int iB = this.b.b(c2680tPM0.L2(), c2680tPM0.e);
            int iA = this.b.a(c2680tPM0.K2(), c2680tPM0.e);
            if (iB == iA) {
                return true;
            }
            for (C2680tP c2680tP : this.a) {
                int iB2 = this.b.b(c2680tP.L2(), c2680tP.e);
                int iA2 = this.b.a(c2680tP.K2(), c2680tP.e);
                if (iB2 == iB && iA2 == iA) {
                    return true;
                }
                if (iA2 == iB && iB2 == iA) {
                    if (!c2680tPM0.I2().b()) {
                        return true;
                    }
                    if (iB != iA + 1 && iB + 1 != iA) {
                        return true;
                    }
                }
            }
        }
        if (abstractC0890Uw.c() != null && abstractC0890Uw.c().T()) {
            final C2543rl0 c2543rl0C = abstractC0890Uw.c();
            final int iA3 = this.b.a(c2543rl0C, abstractC0890Uw.e);
            this.a.removeIf(new Predicate() { // from class: s5g
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.a(c2543rl0C, iA3, (C2680tP) obj);
                }
            });
        }
        if (!abstractC0890Uw.i2()) {
            return false;
        }
        this.a.add(abstractC0890Uw.m0());
        return false;
    }

    public final boolean a(C2543rl0 c2543rl0, int i, C2680tP c2680tP) {
        int iB = this.b.b(c2680tP.L2(), c2680tP.e);
        int iA = this.b.a(c2680tP.K2(), c2680tP.e);
        for (int i2 = 0; i2 < c2543rl0.o.O(); i2++) {
            for (int i3 = 0; i3 < c2680tP.c().o.O(); i3++) {
                int i4 = i + i2;
                if (i4 == iA + i3 || i4 == iB + i3) {
                    return true;
                }
            }
        }
        return false;
    }
}
