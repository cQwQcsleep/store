package com.android.tools.r8.shaking;

import com.android.tools.r8.experimental.graphinfo.GraphNode;
import com.android.tools.r8.graph.B5;
import com.android.tools.r8.internal.C2807us;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class D1 {
    public static C3479x1 a(B5 b5) {
        return new C3479x1(b5.e());
    }

    public static C3454s1 b(B5 b5) {
        return new C3454s1(b5.a(), b5.e());
    }

    public static C3489z1 c(B5 b5) {
        return new C3489z1(b5.e());
    }

    public abstract GraphNode a(N0 n0);

    public abstract C2807us.a a();

    public static C3474w1 a(com.android.tools.r8.graph.I2 i2) {
        return new C3474w1(i2);
    }

    public boolean b() {
        return this instanceof C3489z1;
    }
}
