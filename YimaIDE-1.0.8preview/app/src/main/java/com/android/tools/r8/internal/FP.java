package com.android.tools.r8.internal;

import com.android.tools.r8.internal.FP;
import com.android.tools.r8.internal.Kh0;
import com.android.tools.r8.origin.Origin;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class FP extends EJ {
    public FP(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50) {
        super(b1, c2742u50, false, 1);
    }

    public static /* synthetic */ void b(Kh0 kh0) {
    }

    public final C0891Ux a(String str) {
        JJ.a aVarA;
        AbstractC1643hD abstractC1643hDA = a(this.f, str);
        C0891Ux c0891Ux = new C0891Ux();
        for (Iterator it = abstractC1643hDA.c().b.iterator(); it.hasNext(); it = it) {
            C1898kD c1898kDD = ((AbstractC1643hD) it.next()).d();
            int iB = a(c1898kDD, "api_level_below_or_equal").b();
            if (c0891Ux.a(iB)) {
                JJ jj = (JJ) c0891Ux.get(iB);
                com.android.tools.r8.graph.B1 b1 = this.a;
                C2742u50 c2742u50 = this.b;
                if (!EJ.h && this.e == null) {
                    x1f.a();
                    return null;
                }
                Origin origin = this.e;
                jj.getClass();
                aVarA = new JJ.a(b1, c2742u50, origin, jj.a, jj.b, jj.c, jj.d, jj.e, jj.f, jj.g, jj.h);
            } else {
                com.android.tools.r8.graph.B1 b2 = this.a;
                C2742u50 c2742u51 = this.b;
                if (!EJ.h && this.e == null) {
                    x1f.a();
                    return null;
                }
                aVarA = JJ.a(b2, c2742u51, this.e);
            }
            EJ.a(c1898kDD, aVarA);
            c0891Ux.a(iB, aVarA.a());
        }
        return c0891Ux;
    }

    public EP c(com.android.tools.r8.t0 t0Var) {
        MJ mjA = a(new Consumer() { // from class: ql4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FP.b((Kh0) obj);
            }
        }, b(t0Var));
        C0891Ux c0891UxA = a("common_flags");
        C0891Ux c0891UxA2 = a("library_flags");
        C0891Ux c0891UxA3 = a("program_flags");
        if (EJ.h || this.e != null) {
            return new EP(this.e, mjA, c0891UxA, c0891UxA2, c0891UxA3);
        }
        x1f.a();
        return null;
    }
}
