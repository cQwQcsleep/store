package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2035lp extends AbstractC2849vN {
    public final com.android.tools.r8.graph.I2 c;
    public final com.android.tools.r8.graph.I2 d;

    public C2035lp(C0245l1 c0245l1, C0245l1 c0245l2, com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3) {
        super(c0245l1, c0245l2);
        this.c = i2;
        this.d = i3;
    }

    public final com.android.tools.r8.graph.I2 a(Function function) {
        if (d()) {
            return (com.android.tools.r8.graph.I2) function.apply(this.c);
        }
        return null;
    }

    public final com.android.tools.r8.graph.I2 b(Function function) {
        com.android.tools.r8.graph.I2 i2 = this.d;
        if (i2 != null) {
            return (com.android.tools.r8.graph.I2) function.apply(i2);
        }
        return null;
    }

    public final com.android.tools.r8.graph.I2 c() {
        return this.c;
    }

    public final boolean d() {
        return this.c != null;
    }
}
