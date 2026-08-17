package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.F4;
import com.android.tools.r8.internal.C2119mo;
import com.android.tools.r8.internal.PO;
import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class l4 {
    public static final PO c = PO.a;
    public l4 a;
    public final HashMap b = new HashMap();

    public l4(l4 l4Var) {
        this.a = l4Var;
    }

    public final int a(C0231j1 c0231j1) {
        PO po = c;
        C0322w2 reference = c0231j1.getReference();
        po.getClass();
        C2119mo c2119mo = new C2119mo(po, reference);
        C0231j1 c0231j1A = a(c2119mo);
        if (c0231j1A == null) {
            this.b.put(c2119mo, c0231j1);
            return 2;
        }
        F4 f4 = c0231j1.g;
        F4 f5 = c0231j1A.g;
        String strE0 = c0231j1.E0().E0();
        String strE1 = c0231j1A.E0().E0();
        int iE = f4.e();
        if (iE <= f5.e() && !(iE == f5.e() && f4.q() && !strE0.equals(strE1))) {
            return 1;
        }
        this.b.put(c2119mo, c0231j1);
        return 3;
    }

    public final C0231j1 a(C2119mo c2119mo) {
        C0231j1 c0231j1 = (C0231j1) this.b.get(c2119mo);
        if (c0231j1 != null) {
            return c0231j1;
        }
        l4 l4Var = this.a;
        if (l4Var != null) {
            return l4Var.a(c2119mo);
        }
        return null;
    }

    public final void a(l4 l4Var) {
        this.a = l4Var;
    }
}
