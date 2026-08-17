package com.android.tools.r8.naming;

import com.android.tools.r8.graph.C0333y;
import java.util.IdentityHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class R0 extends C {
    public static final /* synthetic */ boolean e = true;
    public R0 d;

    public R0(C0333y c0333y) {
        super(c0333y, new IdentityHashMap());
        this.d = null;
    }

    public final void a(R0 r0) {
        R0 r1;
        for (Map.Entry entry : r0.c.entrySet()) {
            ((Q0) b()).a.putAll(((Q0) entry.getValue()).a);
        }
        R0 r2 = r0.d;
        if (r2 != null) {
            if (e || (r1 = this.d) == null || r1 == r2) {
                this.d = r2;
            } else {
                x1f.a();
            }
        }
    }

    @Override // com.android.tools.r8.naming.C
    public final Object a() {
        return new Q0();
    }
}
