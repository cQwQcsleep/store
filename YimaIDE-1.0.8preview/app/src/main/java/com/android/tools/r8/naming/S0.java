package com.android.tools.r8.naming;

import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C2742u50;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S0 extends x0 {
    public final HashMap a = new HashMap();
    public final HashSet b = new HashSet();
    public final C2742u50 c;

    public S0(C2742u50 c2742u50) {
        this.c = c2742u50;
    }

    @Override // com.android.tools.r8.naming.x0
    public final AbstractC3323g a(String str, String str2, F0 f0) {
        String strI = C0929Wj.I(str2);
        String strI2 = C0929Wj.I(str);
        this.b.add(strI2);
        C3327i c3327i = new C3327i(strI2, strI, f0, this.c);
        if (!str.startsWith("R8$$REMOVED$$CLASS$$") && this.a.put(strI, c3327i) != null) {
            C2742u50 c2742u50 = this.c;
            int i = z0.f;
            c2742u50.error(new z0("'" + C0929Wj.b(strI) + "' already has a mapping", f0));
        }
        return c3327i;
    }

    @Override // com.android.tools.r8.naming.x0
    public final x0 a(String str, String str2) {
        return this;
    }

    @Override // com.android.tools.r8.naming.x0
    public final void a(String str) {
    }

    @Override // com.android.tools.r8.naming.x0
    public final x0 a(com.android.tools.r8.naming.mappinginformation.b bVar) {
        return this;
    }
}
