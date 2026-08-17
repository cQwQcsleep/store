package com.android.tools.r8.internal;

import com.android.tools.r8.internal.DP;
import com.android.tools.r8.internal.Kh0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class DP extends C2210nt {
    public DP(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50) {
        super(b1, c2742u50, false, 1);
    }

    public static /* synthetic */ void b(Kh0 kh0) {
    }

    public final HashMap a(String str) {
        O2 o2;
        C2552rt.a aVarA;
        AbstractC1643hD abstractC1643hDA = a(this.h, str);
        HashMap map = new HashMap();
        Iterator it = abstractC1643hDA.c().b.iterator();
        while (it.hasNext()) {
            C1898kD c1898kDD = ((AbstractC1643hD) it.next()).d();
            int iB = a(c1898kDD, "api_level_below_or_equal").b();
            if (c1898kDD.b.containsKey("api_level_greater_or_equal")) {
                o2 = new O2(EnumC3077y2.b(iB), EnumC3077y2.b(((AbstractC1643hD) c1898kDD.b.get("api_level_greater_or_equal")).b()));
            } else {
                o2 = new O2(EnumC3077y2.b(iB), null);
            }
            if (map.containsKey(o2)) {
                C2552rt c2552rt = (C2552rt) map.get(o2);
                C2742u50 c2742u50 = this.d;
                if (!C2210nt.i && this.g == null) {
                    x1f.a();
                    return null;
                }
                aVarA = c2552rt.b(c2742u50, this.g);
            } else {
                C2742u50 c2742u51 = this.d;
                if (!C2210nt.i && this.g == null) {
                    x1f.a();
                    return null;
                }
                aVarA = C2552rt.a(c2742u51, this.g);
            }
            a(c1898kDD, aVarA);
            map.put(o2, aVarA.a());
        }
        return map;
    }

    public C3192zP c(com.android.tools.r8.t0 t0Var) {
        C0394Bt c0394BtA = a(new Consumer() { // from class: y73
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DP.b((Kh0) obj);
            }
        }, b(t0Var));
        HashMap mapA = a("common_flags");
        HashMap mapA2 = a("library_flags");
        HashMap mapA3 = a("program_flags");
        if (C2210nt.i || this.g != null) {
            return new C3192zP(this.g, c0394BtA, mapA, mapA2, mapA3);
        }
        x1f.a();
        return null;
    }
}
