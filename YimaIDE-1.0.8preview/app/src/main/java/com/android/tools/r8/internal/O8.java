package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class O8 {
    public static O8 a(C0333y c0333y) {
        ArrayList arrayList = new ArrayList();
        if (c0333y.M().K1.a) {
            LY ly = (!c0333y.M().K1.a || c0333y.M().K1.c.k.isEmpty()) ? null : new LY(c0333y);
            if (ly != null) {
                arrayList.add(ly);
            }
            if (!C0696Nk.c && !c0333y.M().K1.a) {
                x1f.a();
                return null;
            }
            C0696Nk c0696Nk = c0333y.M().K1.c.h().isEmpty() ? null : new C0696Nk(c0333y);
            if (c0696Nk != null) {
                arrayList.add(c0696Nk);
            }
            arrayList.add(new C0956Xk(c0333y));
        }
        C2137n20 c2137n20 = c0333y.M().o0() ? new C2137n20(c0333y) : null;
        if (c2137n20 != null) {
            arrayList.add(c2137n20);
        }
        Jl0 jl0 = c0333y.M().p0() ? new Jl0(c0333y) : null;
        if (jl0 != null) {
            arrayList.add(jl0);
        }
        return arrayList.isEmpty() ? new M8() : new N8(c0333y, arrayList);
    }

    public abstract void a(ExecutorService executorService, Q8 q8);
}
