package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.internal.C2543rl0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class k0 {
    public static final /* synthetic */ boolean c = true;
    public final C0245l1 a;
    public final C2543rl0 b;

    public k0(C0245l1 c0245l1, C2543rl0 c2543rl0) {
        if (!c && c2543rl0 != c2543rl0.h()) {
            x1f.a();
            throw null;
        }
        this.a = c0245l1;
        this.b = c2543rl0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof k0)) {
            return false;
        }
        k0 k0Var = (k0) obj;
        return k0Var.b == this.b && k0Var.a == this.a;
    }

    public final int hashCode() {
        return (this.a.hashCode() * 7) + this.b.b;
    }
}
