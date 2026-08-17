package com.android.tools.r8.internal;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GW extends HW {
    public GW(com.android.tools.r8.shaking.V1 v1, List list) {
        super(v1, list);
    }

    @Override // com.android.tools.r8.internal.HW
    public final boolean a(Object obj, com.android.tools.r8.shaking.M m) {
        com.android.tools.r8.graph.F2 f2 = (com.android.tools.r8.graph.F2) obj;
        if (com.android.tools.r8.shaking.M.w0 || m.p.u1.b()) {
            return m.E.contains(f2);
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.HW
    public final List a(List list) {
        return list;
    }
}
