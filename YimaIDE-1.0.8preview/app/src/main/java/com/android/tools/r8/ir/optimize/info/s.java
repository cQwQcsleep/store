package com.android.tools.r8.ir.optimize.info;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.E0;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class s {
    public static final s b = new s(Collections.EMPTY_MAP);
    public final Map a;

    public s(Map map) {
        this.a = map;
    }

    public final h a(E0 e0, C0231j1 c0231j1) {
        C3263d c3263d = C3263d.b;
        return !e0.a0() ? c3263d : (h) this.a.getOrDefault(c0231j1.getReference(), c3263d);
    }
}
